package com.wentjiang.crawler.common.task;

import com.wentjiang.crawler.util.DownloadUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by jiangwentao on 12/8/2016 7:47 PM.
 */
@Slf4j
public class ProxyGetterTask {
    public void getProxy(){
        log.info("start get proxy address");
        String url = "http://api.xicidaili.com/free2016.txt";
        String file = "proxy.txt";
        try {
            DownloadUtil.downloadFile(url, file);
        } catch (IOException e) {
            log.info("get proxy address error");
            e.printStackTrace();
        }
    }
}
