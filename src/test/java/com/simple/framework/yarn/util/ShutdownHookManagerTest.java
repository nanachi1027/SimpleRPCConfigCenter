package com.simple.framework.yarn.util;

import com.simple.framework.yarn.service.CompositeService;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * @Author: Mae
 * @Date: 2020/8/10 11:49 下午
 */
public class ShutdownHookManagerTest {
    @Test
    public void testShutdownHookManager() {

        // here we create two mock services
        MockService1 mockService1 = new MockService1("low");
        MockService1 mockService2 = new MockService1("normal");
        MockService1 mockService3 = new MockService1("high");

        // register services to shutdown hook manager
        CompositeService.CompositeServiceShutdownHook mockService1ShutdownHook =
                new CompositeService.CompositeServiceShutdownHook(mockService1);
        CompositeService.CompositeServiceShutdownHook mockService2ShutdownHook =
                new CompositeService.CompositeServiceShutdownHook(mockService2);

        CompositeService.CompositeServiceShutdownHook mockService3ShutdownHook =
                new CompositeService.CompositeServiceShutdownHook(mockService3);

        // low priority will be called lasted
        ShutdownHookManager.getInstance().addShutdownHook(mockService1ShutdownHook, MockServicePriority.LOW_PRIORITY.priority);
        ShutdownHookManager.getInstance().addShutdownHook(mockService2ShutdownHook, MockServicePriority.NORMAL_PRIORITY.priority);
        ShutdownHookManager.getInstance().addShutdownHook(mockService3ShutdownHook, MockServicePriority.HIGH_PRIORITY.priority);
    }

    @Test
    public void testAtomicBoolean () {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        System.out.println(atomicBoolean.getAndSet(true));
    }

    enum MockServicePriority {
        HIGH_PRIORITY(10, "higest"),
        NORMAL_PRIORITY(5, "normal"),
        LOW_PRIORITY(1, "lowest");

        private int priority;
        private String description;

        MockServicePriority(int priority, String description) {
            this.priority = priority;
            this.description = description;
        }


        public static MockServicePriority getByPriority(int priority) {
            for (MockServicePriority p : MockServicePriority.values()) {
                if (p.getPriority() == priority) {
                    return p;
                }
            }
            throw new RuntimeException("Can not find Priority by given priority " + priority);
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

class MockService1 extends CompositeService {
    private String name;

    public MockService1(String name) {
        this.name = name;
    }

    @Override
    public void stop() {
        System.out.println(name + " service is invoked to shutdown");
    }
}