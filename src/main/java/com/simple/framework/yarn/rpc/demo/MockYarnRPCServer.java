package com.simple.framework.yarn.rpc.demo;

import com.simple.framework.yarn.rpc.proto.TestRpcServiceProtos;
import com.simple.framework.yarn.rpc.service.MockConfigCenter;
import com.simple.framework.yarn.rpc.service.MockConfigCenterImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @Author: Mae
 * @Date: 2020/8/10 8:15 上午
 */
public class MockYarnRPCServer {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        RPC.setProtocolEngine(conf, MockConfigCenter.class, ProtobufRpcEngine.class);
        RPC.Server server = null;
        try {
            server = new RPC.Builder(conf)
                    .setBindAddress("localhost").setPort(8092)
                    .setProtocol(MockConfigCenter.class)
                    .setInstance(TestRpcServiceProtos.TestConfigCenterService.newReflectiveBlockingService(new MockConfigCenterImpl()))
                    .setNumHandlers(4)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }
}
