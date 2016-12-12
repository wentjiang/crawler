package com.wentjiang.crawler.util;

import com.wentjiang.crawler.model.ProxyInfo;
import org.junit.Test;

/**
 * Created by jiangwentao on 12/9/2016 10:36 AM.
 */
public class ProxyUtilTest {
    @Test
    public void proxyUseable() throws Exception {
        ProxyInfo info = new ProxyInfo();
        info.setIpAddress("120.92.3.127");
        info.setProt("80");
        System.out.println(ProxyUtil.proxyUseable(info));
    }

    @Test
    public void proxyBreakWall() throws Exception {

    }

}