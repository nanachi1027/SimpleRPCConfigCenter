package com.simple.framework.yarn.rpc.service;

import com.simple.framework.yarn.rpc.proto.TestRpcServiceProtos;
import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * @Author: Mae
 * @Date: 2020/8/10 7:42 上午
 */
@ProtocolInfo(protocolName = "service.TestRpcService", protocolVersion = 1)
public interface MockConfigCenter extends TestRpcServiceProtos.TestConfigCenterService.BlockingInterface {
}
