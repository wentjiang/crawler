package com.wentjiang;

import com.wentjiang.crawler.http.HttpClientFactory;

/**
 * Created by jiangwentao on 12/5/2016 4:59 PM.
 */
public class HttpClientFactoryTest {
    @org.junit.Test
    public  void mytest() {
        String baidu = HttpClientFactory.invokeGet("http://www.wanmei.com");
        System.out.println(baidu);
    }
}
