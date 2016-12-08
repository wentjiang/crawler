package com.wentjiang.crawler.util;

import com.wentjiang.crawler.model.ProxyInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * Created by wentjiang on 2016/12/8.
 */
@Slf4j
public class IPProxyInfoUtil {
    //从服务器端请求代理地址
    public static void getRemoteProxyInfo(){
        String url = "http://api.xicidaili.com/free2016.txt";
        String file = "proxy.txt";
        try {
            DownloadUtil.downloadFile(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProxyInfo(List<ProxyInfo> proxies){
        //加载代理文件
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
}
