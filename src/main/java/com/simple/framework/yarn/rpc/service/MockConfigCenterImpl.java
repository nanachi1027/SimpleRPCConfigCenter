package com.simple.framework.yarn.rpc.service;

import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.simple.framework.yarn.rpc.proto.TestRpcServiceProtos;

/**
 * @Author: Mae
 * @Date: 2020/8/10 7:43 上午
 */
public class MockConfigCenterImpl implements MockConfigCenter {
    public TestRpcServiceProtos.ResponseProto ping(RpcController controller, TestRpcServiceProtos.RequestProto request) throws ServiceException {
        String str = request.getMessage();
        System.out.println("(method:ping)[received request]: " + str);
        TestRpcServiceProtos.ResponseProto responseProto = TestRpcServiceProtos.ResponseProto.newBuilder()
                .setMessage("Ping received request " + str)
                .build();
        return responseProto;
    }

    public TestRpcServiceProtos.ResponseProto echo(RpcController controller, TestRpcServiceProtos.RequestProto request) throws ServiceException {
        String str = request.getMessage();
        System.out.println("(method:echo)(received request): " + str);
        TestRpcServiceProtos.ResponseProto responseProto = TestRpcServiceProtos.ResponseProto.newBuilder()
                .setMessage("Echo received request " + str)
                .build();
        return responseProto;
    }
}
