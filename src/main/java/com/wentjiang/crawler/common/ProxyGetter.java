package com.wentjiang.crawler.common;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static com.wentjiang.crawler.http.HttpClientFactory.invokeGet;

/**
 * Created by jiangwentao on 12/8/2016 2:35 PM.
 */
public class ProxyGetter {

    public static void getProxy(){
        String url = "http://www.xicidaili.com";
        String html = invokeGet(url);
        Document document = Jsoup.parse(html);

    }

    @Data
    class Proxy{
        private String ip;
        private String port;
        private String address;
        private String type;
        private Integer alive;
    }

}
