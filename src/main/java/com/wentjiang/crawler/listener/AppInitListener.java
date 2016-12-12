package com.wentjiang.crawler.listener;

import com.wentjiang.crawler.http.instant.HttpClientPoolIdleMonitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jiangwentao on 12/8/2016 7:53 PM.
 */
public class AppInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //添加httpclient的监控
        HttpClientPoolIdleMonitor.init();
    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        HttpClientPoolIdleMonitor.shutdown();
    }

}
