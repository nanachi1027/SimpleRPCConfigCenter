package com.simple.framework.yarn.rpc;

import com.google.protobuf.ServiceException;
import com.simple.framework.yarn.rpc.proto.TestRpcServiceProtos;
import com.simple.framework.yarn.rpc.service.MockConfigCenter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author: Mae
 * @Date: 2020/8/10 8:05 上午
 */
public class YarnRPCClientTest {
    @Test
    public void runRPCClient() {
        InetSocketAddress addr = new InetSocketAddress("localhost", 8092);
        Configuration conf = new Configuration();
        RPC.setProtocolEngine(conf, MockConfigCenter.class, ProtobufRpcEngine.class);
        try {
            MockConfigCenter mockConfigCenter = RPC.getProxy(
                    MockConfigCenter.class, RPC.getProtocolVersion(MockConfigCenter.class),
                    addr, conf);
            TestRpcServiceProtos.RequestProto requestProto = TestRpcServiceProtos.RequestProto.newBuilder()
                    .setMessage("Hello Message")
                    .build();

            TestRpcServiceProtos.ResponseProto responseProtoEcho = mockConfigCenter.echo(null, requestProto);
            System.out.println("Response info " + responseProtoEcho.getMessage());

            TestRpcServiceProtos.ResponseProto responseProtoPing = mockConfigCenter.ping(null, requestProto);
            System.out.println("Response info " + responseProtoPing.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
