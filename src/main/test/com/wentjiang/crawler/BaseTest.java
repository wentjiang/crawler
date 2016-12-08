package com.wentjiang.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiangwentao on 12/7/2016 5:57 PM.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合
@ContextConfiguration(locations="classpath:conf/applicationContext*.xml") // 加载配置
public class BaseTest {
    @Test
    public void test(){
        System.out.println("hello world");
    }
}
