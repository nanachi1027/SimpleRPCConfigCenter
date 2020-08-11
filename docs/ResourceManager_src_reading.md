* Q: ResourceManager 的入口函数
> 通过 `start-yarn.sh` 所调用的 `${HADOOP_YARN_HOME}/bin/yarn` 脚本查看 `bin/yarn` 可知 ResourceManager#main 是 RM 服务启动的入口
```shell script
# bin/yarn
...
resourcemanager)
HADOOP_CLASSNAME='org.apache.hadoop.yarn.server.resourcemanager.ResourceManager'
...
```
```java
// ResourceManager 

main(...) {
    // 
    Thread.setDefaultUncaughtExceptionHandler(new YarnUncaughtExceptionHandler());
   // 
    StringUtils.startupShutdownMessage(ResourceManager.class, argv, LOG);
  ...
  ResourceManager resourceManager = new ResourceManager();
 
  // 将 ResourceManager 通过 CompositeServiceShutdownHook 增加一层 Runnable 接口调用，
  // 用于 ShutdownHookManager 调用 Runnable 来指向 ResourceManager 的线程实例并将其停止; 
  ShutdownHookManager.get().addShutdownHook(
     new CompositeServiceShutdownHook(resourceManager),
     SHUTDOWN_HOOK_PRIORITY);

  resourceManager.init(conf);
  resourceManager.start();
 ...
}






```
