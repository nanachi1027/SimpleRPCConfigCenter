In this project we extract Yarn's RPC framework based on which supports implements simple RPC services communication.
In first push, we implement a simple RPC client and server based on 'test_rpc_service.proto' in which we define the 
message and the rpc services.

And we also add maven plugin which supports generate compile proto to corresponding java classes under `com.simple.framework.yarn.rpc.proto`.

Instead of installing protoc to your host and compile by manual, just execute 'mvn clean package' the classes will be generated.

Actual implementations of the rpc service classes are under `com.simple.framework.yarn.proc.service`.

In next comples version, we gonna use this RPC framework to build our config center which supports hot loading parameters without re-starting running services. 
