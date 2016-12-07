package com.wentjiang.crawler;

import com.wentjiang.crawler.util.HttpUtil;

/**
 * Created by wentjiang on 2016/12/7.
 */
public class HttpClientTest {
    public static void main(String[] args) {
        String html = HttpUtil.httpGet("http://t66y.com");
        System.out.println(html);

    }
}
