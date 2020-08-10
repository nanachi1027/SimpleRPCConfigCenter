package com.simple.framework.yarn.service;

/**
 * @Author: Mae
 * @Date: 2020/8/10 11:45 下午
 */

public class CompositeService {
    public void stop() {

    }
    public static class CompositeServiceShutdownHook implements Runnable {

        private CompositeService compositeService;

        public CompositeServiceShutdownHook(CompositeService compositeService) {
            this.compositeService = compositeService;
        }

        @Override
        public void run() {
            compositeService.stop();
        }
    }
}