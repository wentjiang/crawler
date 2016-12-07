package com.wentjiang.crawler.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class HttpUtil {
    public static String httpGet(String url) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        result = getResult(httpClient, httpGet);
        return result;
    }

    public static String httpsGet(String url) {
        String result = null;
        CloseableHttpClient httpClient = SSLUtil.createSSLInsecureClient();
        HttpGet httpGet = new HttpGet(url);
        result = getResult(httpClient, httpGet);
        return result;
    }

    private static String getResult(CloseableHttpClient httpClient, HttpGet httpGet) {
        String result = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                result = readResponse(entity, "utf-8");
            }
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static String readResponse(HttpEntity resEntity, String charset) {
        StringBuilder res = new StringBuilder();
        BufferedReader reader = null;
        if (resEntity == null) {
            return null;
        }
        try {
            reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), charset));
            String line;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res.toString();
    }
}
