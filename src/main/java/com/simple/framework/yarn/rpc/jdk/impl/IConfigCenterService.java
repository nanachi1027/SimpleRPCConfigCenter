package com.simple.framework.yarn.rpc.jdk.impl;

import org.apache.hadoop.conf.Configuration;

/**
 * @Author: Mae
 * @Date: 2020/8/10 2:07 上午
 */
public interface IConfigCenterService {
    Configuration getAppConfig(GetApplicationConfigRequest request);

    class GetApplicationConfigRequest {
        String appName;
        String queueName;
        String appVersion;
        Configuration confs;
    }
}
