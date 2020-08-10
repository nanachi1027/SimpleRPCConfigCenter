package com.simple.framework.yarn.rpc.jdk.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Mae
 * @Date: 2020/8/10 2:12 上午
 */
public class ConfigCenterServiceInvocationHandler implements InvocationHandler {
    IConfigCenterService iConfigCenterService;

    public ConfigCenterServiceInvocationHandler(IConfigCenterService iConfigCenterService) {
        this.iConfigCenterService = iConfigCenterService;
    }

    public IConfigCenterService getProxy() {
        return (IConfigCenterService) Proxy.newProxyInstance(iConfigCenterService.getClass().getClassLoader(),
                iConfigCenterService.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
