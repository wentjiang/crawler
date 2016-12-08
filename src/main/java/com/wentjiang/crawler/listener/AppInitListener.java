package com.wentjiang.crawler.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jiangwentao on 12/8/2016 7:53 PM.
 */
public class AppInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initProxyInfo();


    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void initProxyInfo() {
    }
}
