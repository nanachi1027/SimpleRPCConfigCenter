#### 由线上问题定位的 Spark 事件总线代码阅读
* 关于 Spark 2.0 之前的事件总线 drop event 丢 event 引发一系列问题分析. 
* 相关的 [jira-18838](https://issues.apache.org/jira/browse/SPARK-18838)
* 问题背景:
```txt
Spark SQL 计算任务并发 1k 左右, 计算任务部署在线上之前压测运行时长: 5 天后 WEB UI 中出现了大量的 active job 处于 pending 的状态信息. 

在 driver 主机上通过 jstat -gcutil ${pid} 得到 driver 进程堆内空间打满. 

使用 jmap -heap ${pid} 生成内存快照, 将快照从服务器拷贝到本地主机, 使用 MemroyAnalyzer 进行内存分析可以看到, AsynchronousListenerBus 中的
LinkedBlockingQueue 这个 event 队列打满之后(默认上限 event 的数目是 10000, 控制参数是: ), 开始 drop event, 
而这些 event 一旦涉及到资源分配, executor 调度的话便会造成一些列 block 的问题, 最严重的会触发 job 挂掉重新计算. 

2.0 版本之前 event 总线是单线程串行的, 所有的 event 都放到 1 个 queue 中, 一旦 1 个 event 执行较慢, 会影响后续 event 的处理.
一旦 queue 打满, 便会引起 queue drop event. 


jira-18838 中所讨论的就是针对上述问题的, 在了解 jira 中讨论的问题之前, 需要先了解下 Spark 中 Event 中的基本构成对象. 
因为 Spark 是事件触发模型, 其实现方式可以看做是一个观察模式来监控 & 响应 & 处理各种 Event. 

首先, 先来回忆下观察者模式, 观察者模式 = 消息发布方 + 消息订阅方; 
消息发布方支持的基本操作 & 成员变量
0. List<Subject> subjects;  // 记录着登记了的消息订阅方;
1. register(Subject)  // 供消息订阅方来调用, 将消息订阅方加到订阅方 list 中;
2. unregister(Subject) // 供消息订阅方调用, 将消息订阅方从 list 中移除; 
3. pushToAll() // 当 event 到来之后, 遍历 subjects 中的每个 Subject 然后调用 subject#process(event)

消息订阅方基本操作:
1. process(Event) // 不同的消息订阅方实现 process 的逻辑不同, 对应了不同订阅者对接收到不同 event 处理的方式的不同. 

对照着这个模型, 消息发布方就是 ListenerBus, 事件就是 Event, 消息订阅 & 处理方就是 Listener. 
ListenerBus 中会维护一个 Listener 数组, 提供 add/remove listener 的方法, 以及接收到新 event 之后, 遍历所有 listener 调用 listener 的处理方法来处理不同的 event. 

----

接着来说, 这个 jira 讨论的问题:
1. Driver 端的 ListenerBus 是所有 event 处理的瓶颈点; 

2. 很多重要模块都是通过监听的方式来等待 ListenerBus 将新到达的 event 喂给他们. 

3. 一旦排在他们前面的 listener 处理 event 耗时过久, 或是 event 数量增大, 重要模块接收到该处理的 event 延迟到达或是直接就被丢掉了, 
   便会导致 job block, pending, retry 甚至挂掉超出阈值上限引起 App 挂掉.

以上述问题为出发点, 当期那 jira 中提出了将单线程, 串行处理 event 的事件处理模型拆分成多队列 + 异步处理 event 的消息处理模式. 
这样一来, 1 个 event 到来之后, 会被分发到 4 个不同的队列中, 特定的 listener 订阅特定 queue 中的 event, 每个 event queue 有一个线程池来处理. 

负责接收 post event 的单线程池确保接收和转发 event 是顺序性的. 

> ListenerBus & SparkListenerBus & LiveListenerBus 源码注释阅读; 



  






```
