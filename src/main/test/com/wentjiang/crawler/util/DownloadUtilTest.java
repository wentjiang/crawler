package com.wentjiang.crawler.util;

import org.junit.Test;

/**
 * Created by jiangwentao on 12/8/2016 3:02 PM.
 */
public class DownloadUtilTest {
    @Test
    public void downloadFile() throws Exception {
        String url = "http://api.xicidaili.com/free2016.txt";
        String file = "proxy.txt";
        DownloadUtil.downloadFile(url, file);
    }
}