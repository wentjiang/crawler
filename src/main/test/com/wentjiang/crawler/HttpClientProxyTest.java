package com.wentjiang.crawler;

import com.wentjiang.crawler.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wentjiang on 2016/12/7.
 */
public class HttpClientProxyTest {
    public static void main(String[] args) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpHost proxy = new HttpHost("121.31.195.96",8123,"http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        String url1 = "http://www.wanmei.com";
        HttpGet httpGet = new HttpGet(url1);
        httpGet.setConfig(config);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            // getEntity()
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                System.out.println("response:" + EntityUtils.toString(httpEntity, "UTF-8"));
                HttpUtil.readResponse(httpEntity,"utf-8");
            }
            // 释放资源
            Thread.sleep(100);
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
