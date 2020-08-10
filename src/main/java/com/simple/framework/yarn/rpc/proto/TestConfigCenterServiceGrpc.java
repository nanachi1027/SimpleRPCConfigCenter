/*
this class has no use in current project, just ignore this
package com.simple.framework.yarn.rpc.service;

import com.simple.framework.yarn.rpc.proto.TestRpcServiceProtos;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

*/
/**
 * Creates a new async stub that supports all call types for the service
 * <p>
 * Creates a new blocking-style stub that supports unary and streaming output calls on the service
 * <p>
 * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
 *//*

@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.15.0)",
    comments = "Source: test_rpc_service.proto")
public class TestConfigCenterServiceGrpc {

  private TestConfigCenterServiceGrpc() {}

  public static final String SERVICE_NAME = "com.simple.framework.yarn.rpc.pb.impl.TestConfigCenterService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<TestRpcServiceProtos.RequestProto,
      TestRpcServiceProtos.ResponseProto> METHOD_PING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.simple.framework.yarn.rpc.pb.impl.TestConfigCenterService", "ping"),
          io.grpc.protobuf.ProtoUtils.marshaller(TestRpcServiceProtos.RequestProto.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(TestRpcServiceProtos.ResponseProto.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<TestRpcServiceProtos.RequestProto,
      TestRpcServiceProtos.ResponseProto> METHOD_ECHO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.simple.framework.yarn.rpc.pb.impl.TestConfigCenterService", "echo"),
          io.grpc.protobuf.ProtoUtils.marshaller(TestRpcServiceProtos.RequestProto.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(TestRpcServiceProtos.ResponseProto.getDefaultInstance()));

  */
/**
 * Creates a new async stub that supports all call types for the service
 *//*

  public static TestConfigCenterServiceStub newStub(io.grpc.Channel channel) {
    return new TestConfigCenterServiceStub(channel);
  }

  */
/**
 * Creates a new blocking-style stub that supports unary and streaming output calls on the service
 *//*

  public static TestConfigCenterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TestConfigCenterServiceBlockingStub(channel);
  }

  */
/**
 * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
 *//*

  public static TestConfigCenterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TestConfigCenterServiceFutureStub(channel);
  }

  */
/**
 *//*

  @java.lang.Deprecated public static interface TestConfigCenterService {

    */
/**
 *//*

    public void ping(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver);

    */
/**
 *//*

    public void echo(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver);
  }

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1469")
  public static abstract class TestConfigCenterServiceImplBase implements TestConfigCenterService, io.grpc.BindableService {

    @java.lang.Override
    public void ping(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PING, responseObserver);
    }

    @java.lang.Override
    public void echo(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ECHO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return TestConfigCenterServiceGrpc.bindService(this);
    }
  }

  */
/**
 *//*

  @java.lang.Deprecated public static interface TestConfigCenterServiceBlockingClient {

    */
/**
 *//*

    public TestRpcServiceProtos.ResponseProto ping(TestRpcServiceProtos.RequestProto request);

    */
/**
 *//*

    public TestRpcServiceProtos.ResponseProto echo(TestRpcServiceProtos.RequestProto request);
  }

  */
/**
 *//*

  @java.lang.Deprecated public static interface TestConfigCenterServiceFutureClient {

    */
/**
 *//*

    public com.google.common.util.concurrent.ListenableFuture<TestRpcServiceProtos.ResponseProto> ping(
        TestRpcServiceProtos.RequestProto request);

    */
/**
 *//*

    public com.google.common.util.concurrent.ListenableFuture<TestRpcServiceProtos.ResponseProto> echo(
        TestRpcServiceProtos.RequestProto request);
  }

  public static class TestConfigCenterServiceStub extends io.grpc.stub.AbstractStub<TestConfigCenterServiceStub>
      implements TestConfigCenterService {
    private TestConfigCenterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestConfigCenterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestConfigCenterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestConfigCenterServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void ping(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PING, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void echo(TestRpcServiceProtos.RequestProto request,
                     io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ECHO, getCallOptions()), request, responseObserver);
    }
  }

  public static class TestConfigCenterServiceBlockingStub extends io.grpc.stub.AbstractStub<TestConfigCenterServiceBlockingStub>
      implements TestConfigCenterServiceBlockingClient {
    private TestConfigCenterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestConfigCenterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestConfigCenterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestConfigCenterServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public TestRpcServiceProtos.ResponseProto ping(TestRpcServiceProtos.RequestProto request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PING, getCallOptions(), request);
    }

    @java.lang.Override
    public TestRpcServiceProtos.ResponseProto echo(TestRpcServiceProtos.RequestProto request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ECHO, getCallOptions(), request);
    }
  }

  public static class TestConfigCenterServiceFutureStub extends io.grpc.stub.AbstractStub<TestConfigCenterServiceFutureStub>
      implements TestConfigCenterServiceFutureClient {
    private TestConfigCenterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestConfigCenterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestConfigCenterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestConfigCenterServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<TestRpcServiceProtos.ResponseProto> ping(
        TestRpcServiceProtos.RequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PING, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<TestRpcServiceProtos.ResponseProto> echo(
        TestRpcServiceProtos.RequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ECHO, getCallOptions()), request);
    }
  }

  @java.lang.Deprecated public static abstract class AbstractTestConfigCenterService extends TestConfigCenterServiceImplBase {}

  private static final int METHODID_PING = 0;
  private static final int METHODID_ECHO = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TestConfigCenterService serviceImpl;
    private final int methodId;

    public MethodHandlers(TestConfigCenterService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((TestRpcServiceProtos.RequestProto) request,
              (io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto>) responseObserver);
          break;
        case METHODID_ECHO:
          serviceImpl.echo((TestRpcServiceProtos.RequestProto) request,
              (io.grpc.stub.StreamObserver<TestRpcServiceProtos.ResponseProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_PING,
        METHOD_ECHO);
  }

  @java.lang.Deprecated public static io.grpc.ServerServiceDefinition bindService(
      final TestConfigCenterService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          METHOD_PING,
          asyncUnaryCall(
            new MethodHandlers<
              TestRpcServiceProtos.RequestProto,
              TestRpcServiceProtos.ResponseProto>(
                serviceImpl, METHODID_PING)))
        .addMethod(
          METHOD_ECHO,
          asyncUnaryCall(
            new MethodHandlers<
              TestRpcServiceProtos.RequestProto,
              TestRpcServiceProtos.ResponseProto>(
                serviceImpl, METHODID_ECHO)))
        .build();
  }
}
*/
