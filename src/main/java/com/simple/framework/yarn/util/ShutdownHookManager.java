package com.simple.framework.yarn.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: Mae
 * @Date: 2020/8/10 10:07 下午
 * <p>
 * ShutdownHookManager 拷贝至 apache hadoop `org.apache.hadoop.util.ShutdownHookManager`;
 * 并对原先逻辑做了简化处理, 去掉了原先 ShutdownHookManager 中与 hadoop 其他包及类中不必要的依赖关系;
 * <p>
 * 说明:
 * ShutdownHookManager 中有 3 个成员比较重要:
 * > 使用 guava ThreadFactoryBuilder 构建的单线程线程池 EXECUTOR.
 * > Set<HookEntry> hooks
 * >
 */
public final class ShutdownHookManager {
    private static final ShutdownHookManager instance = new ShutdownHookManager();

    private static final Logger LOG = LoggerFactory.getLogger(ShutdownHookManager.class);

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(
            new ThreadFactoryBuilder()
                    .setDaemon(true)
                    .setNameFormat("shutdown-hook-%01d")
                    .build());

    private static final TimeUnit TIME_UNIT_DEFAULT = TimeUnit.SECONDS;
    private static final long DEFAULT_SHUTDOWN_TIMEOUT = 20;

    private AtomicBoolean shutdownInProgress = new AtomicBoolean(false);
    private final Set<HookEntry> hooks = Collections.synchronizedSet(new HashSet<HookEntry>());

    static {
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (instance.shutdownInProgress.getAndSet(true)) {
                    LOG.info("Shutdown process invoked a second time: ignoreing");
                }
                long started = System.currentTimeMillis();
                int timeoutCount = instance.executeShutdown();
                long ended = System.currentTimeMillis();

                LOG.debug(String.format("Completed shutdown in %.3f seconds; Timeouts: %d",
                        (ended - started) / 1000.0, timeoutCount));

                // now all of the hooks have executed in order;
                // now shutdown current execute itself
                shutdownExecutor();
            }));
        } catch (IllegalArgumentException ex) {
            LOG.warn("Failed to add the ShutdownHook", ex);
        }
    }

    /**
     * Method to shutdown single thread pool itself.
     */
    private static void shutdownExecutor() {
        try {
            EXECUTOR.shutdown();
            long shutdownTimeout = DEFAULT_SHUTDOWN_TIMEOUT;
            if (!EXECUTOR.awaitTermination(
                    shutdownTimeout,
                    TIME_UNIT_DEFAULT)) {
                // if executor termination failed after timeout
                // then forcefully shutdown executor
                LOG.error("ShutdownHookManager shutdown forcefully after {} seconds.", shutdownTimeout);
                EXECUTOR.shutdown();
            }
        } catch (InterruptedException e) {
            LOG.error("ShutdownHookManager interrupted while waiting for " +
                    " termination.", e);
            EXECUTOR.shutdown();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method responsible for execute all hooks order by thread priority.
     * <p>
     * In method we will transfer each hook ordered by their priority from large to small.
     * And submit each hook's runnable to single thread pool and finally calls each services's stop method.
     */
    private int executeShutdown() {
        int timeouts = 0;
        for (HookEntry entry : getShutdownHooksInOrder()) {
            // take entry and submit it to local single thread pool
            Future<?> future = EXECUTOR.submit(entry.getHook());
            try {
                future.get(entry.getTimeout(), entry.getUnit());
            } catch (Throwable e) {
                timeouts++;
                future.cancel(true);
                LOG.warn("ShutdownHook '" + entry.getHook().getClass().getSimpleName()
                        + "' failed, caused by " + e.toString(), e);
            }
        }
        return timeouts;
    }

    /**
     * Method responsible for sorting each hook in hooks
     * in order by its priority field from large to small.
     */
    private List<HookEntry> getShutdownHooksInOrder() {
        // neither list nor linked list is thread safe
        List<HookEntry> ans;
        synchronized (hooks) {
            ans = new ArrayList<>(hooks);
        }
        // priority max -> min
        Collections.sort(ans, (x, y) -> {
            return y.priority - x.priority;
        });
        return ans;
    }

    public void addShutdownHook(Runnable shutdownHook, int priority) {
        if (instance == null) {
            throw new IllegalArgumentException("ShutdownHook instance cannot be null");
        }
        if (shutdownInProgress.get()) {
            throw new IllegalStateException("Shutdown in progress, cannot add a shutdownHook now");
        }
        hooks.add(new HookEntry(shutdownHook, priority));
    }

    public static ShutdownHookManager getInstance() {
        return instance;
    }

    // == HookEntry class
    static class HookEntry {
        // services' thread reference
        // hooker indexes specified service's thread by its Runnable reference
        private final Runnable hook;

        // thread's priority value, shutdown hooker will
        // shutdown threads order by the thread's priority
        private final int priority;

        // how long will current thread wait for stoping specified thread
        private final long timeout;

        // timeout value's time unit
        private final TimeUnit unit;

        HookEntry(Runnable runnable, int priority) {
            this(runnable, priority,
                    DEFAULT_SHUTDOWN_TIMEOUT,
                    TIME_UNIT_DEFAULT);
        }

        HookEntry(Runnable runnable, int priority, long timeout, TimeUnit unit) {
            hook = runnable;
            this.priority = priority;
            this.timeout = timeout;
            this.unit = unit;
        }

        @Override
        public int hashCode() {
            return hook.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            boolean eq = false;
            if (other != null) {
                if (other instanceof HookEntry) {
                    // only instance from same memory address equals
                    eq = (hook == ((HookEntry) other).hook);
                }
            }
            return eq;
        }

        public Runnable getHook() {
            return hook;
        }

        public int getPriority() {
            return priority;
        }

        public long getTimeout() {
            return timeout;
        }

        public TimeUnit getUnit() {
            return unit;
        }
    }
}
