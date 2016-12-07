package com.wentjiang.crawler.site.t66y;

import com.wentjiang.crawler.util.DownloadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.wentjiang.crawler.http.HttpClientFactory.invokeGet;

/**
 * Created by wentjiang on 2016/12/7.
 */
public class T66yGetter {
    public static void main(String[] args) {
        T66yGetter getter = new T66yGetter();
        getter.getT66y();
    }

    public void getT66y() {
        //爬去douban电影海报
        String url = "http://t66y.com/thread0806.php?fid=16";
        String baseurl = "http://t66y.com/";
        String html = invokeGet(url,null,10000,10000);
        Document doc = Jsoup.parse(html);
        Elements as = doc.getElementsByTag("a");
        List<Element> elements = new ArrayList<>();
        for (Element a : as) {
            String href = a.attr("href");
           if (href.contains("htm_data/16/1612")){
               elements.add(a);
           }
        }
        for (Element element : elements){
            String elementUrl = baseurl + element.attr("href");
            String title = element.getElementsByTag("font").get(0).val();
            Document elementDoc = Jsoup.parse(invokeGet(elementUrl));
            List<Element> pictures = elementDoc.getElementsByTag("input");
            for (Element pic :pictures){
                String picUrl = pic.attr("src");
                String floder = "1024\\"+ title;
                DownloadUtil.get(picUrl,floder);
            }
        }
    }
}
