package com.wentjiang.crawler.util;

import com.wentjiang.crawler.http.HttpClientFactory;
import com.wentjiang.crawler.model.ProxyInfo;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiangwentao on 12/9/2016 10:19 AM.
 */
public class ProxyUtil {
    private static List<ProxyInfo> proxies = new LinkedList<>();
    //判断代理是否可用
    public static boolean proxyUseable(ProxyInfo proxyInfo){
        CloseableHttpClient httpClient = HttpClientFactory.getHttpClientPool().getHttpClient();
        HttpHost proxy = new HttpHost(proxyInfo.getIpAddress(), Integer.parseInt(proxyInfo.getProt()),"http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        String url1 = "http://httpbin.org/get";
        HttpGet httpGet = new HttpGet(url1);
        httpGet.setConfig(config);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //判断代理是否能翻墙
    public static boolean proxyBreakWall(ProxyInfo proxyInfo){
        if (!proxyUseable(proxyInfo)){
            return false;
        }
        //判断代理是否可以翻墙
        return false;
    }
    //通过api获取远程代理地址信息
    public static void getRemoteProxyInfo() {
        String url = "http://api.xicidaili.com/free2016.txt";
        String file = "proxy.txt";
        try {
            DownloadUtil.downloadFile(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //从文件向内存中载入代理信息
    public static void loadProxyInfo() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str;
            fis = new FileInputStream("proxy.txt");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                String[] var0 = str.split(":");
                ProxyInfo info = new ProxyInfo();
                info.setIpAddress(var0[0]);
                info.setProt(var0[1]);
                proxies.add(info);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //对所有的代理地址检查,去除不能用的代理地址
    public static void checkProxies(){
        for (ProxyInfo proxy : proxies){
            if (!proxyUseable(proxy)){
                 proxies.remove(proxy);
            }
        }
    }

}
