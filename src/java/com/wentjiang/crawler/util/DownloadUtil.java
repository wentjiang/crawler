package com.wentjiang.crawler.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class DownloadUtil {
    public static String get(String url) {
        String filename = "";
        List<String> strList = Arrays.asList(url.split("\\."));
        String fileType = null;
        if (strList.size() > 0) {
            fileType = strList.get(strList.size() - 1);
        }
        CloseableHttpClient httpClient = SSLUtil.createSSLInsecureClient();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                filename = download(entity, fileType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private static String download(HttpEntity resEntity, String fileType) {
        String dirPath = "D:\\7.testdownload";
        System.out.println(resEntity.getContentType());
        String fileName = UUID.randomUUID().toString() + "." + fileType;
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String realPath = (dirPath + File.separator).concat(fileName);
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            try {
                filePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = resEntity.getContent();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath.toString();
    }
}
