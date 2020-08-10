package com.simple.framework.yarn.rpc.jdk.impl;

import org.apache.hadoop.conf.Configuration;

import java.util.Iterator;
import java.util.Map;

/**
 * @Author: Mae
 * @Date: 2020/8/10 2:14 上午
 */
public class ConfigCenterServiceImpl implements IConfigCenterService {
    public Configuration getAppConfig(GetApplicationConfigRequest request) {
        String appName = request.appName;
        String appVersion = request.appVersion;
        String queueName = request.queueName;
        Configuration conf = request.confs;
        Iterator<Map.Entry<String, String>> iter = conf.iterator();
        Configuration retConf = new Configuration();

        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            retConf.set(addPrefix(entry.getKey(), "demo"), entry.getValue());
        }

        return retConf;
    }

    private String addPrefix(String configKey, String prefixValue) {
        return prefixValue + "." + configKey;
    }
}
