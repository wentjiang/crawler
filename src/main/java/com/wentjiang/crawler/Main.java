package com.wentjiang.crawler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wentjiang on 2016/12/3.
 * 主启动程序
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext(
                "conf/applicationContext-*.xml");

    }




}
