package com.wentjiang.crawler.util;

import com.wentjiang.crawler.http.HttpClientFactory;
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
    /**
     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
     */
    private String getFileNameByUrl(String url, String contentType) {
        // 移除 "http://" 这七个字符
        url = url.substring(7);
        // 确认抓取到的页面为 text/html 类型
        if (contentType.indexOf("html") != -1) {
            // 把所有的url中的特殊符号转化成下划线
            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
        } else {
            url = url.replaceAll("[\\?/:*|<>\"]", "_") + "."
                    + contentType.substring(contentType.lastIndexOf("/") + 1);
        }
        return url;
    }

    /**
     * 保存网页字节数组到本地文件，filePath 为要保存的文件的相对地址
     */
    private void saveToLocal(byte[] data, String filePath) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(
                    new File(filePath)));
            for (int i = 0; i < data.length; i++)
                out.write(data[i]);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String url, String folder) {
        String filename = "";
        List<String> strList = Arrays.asList(url.split("\\."));
        String fileType = null;
        if (strList.size() > 0) {
            fileType = strList.get(strList.size() - 1);
        }
//        CloseableHttpClient httpClient = SSLUtil.createSSLInsecureClient();
        CloseableHttpClient httpClient = HttpClientFactory.getHttpClientPool().getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                filename = download(entity, fileType, folder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private static String download(HttpEntity resEntity, String fileType, String folder) {
        String dirPath = "D:\\7.testdownload\\" + folder;
//        System.out.println(resEntity.getContentType());
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
