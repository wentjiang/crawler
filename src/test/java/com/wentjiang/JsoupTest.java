package com.wentjiang;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class JsoupTest {
    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p id=\"hahaha\">Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        Element hahaha = doc.getElementById("hahaha");
        System.out.println(hahaha.toString());
        System.out.println(doc.toString());
    }
}
