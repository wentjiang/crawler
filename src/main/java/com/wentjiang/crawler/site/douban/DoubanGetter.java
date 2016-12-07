package com.wentjiang.crawler.site.douban;

import com.wentjiang.crawler.util.DownloadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wentjiang.crawler.http.HttpClientFactory.invokeGet;

/**
 * Created by jiangwentao on 12/5/2016 10:56 AM.
 */
public class DoubanGetter {
    public void getDouban() {
        //爬去douban电影海报
        String url = "https://movie.douban.com/";
        String regex = "https://img\\d.doubanio.com/view/movie_poster_cover/lpst/public/\\w*";
        String html = invokeGet(url);
        Document doc = Jsoup.parse(html);
        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            String imgSrc = img.attr("src");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(imgSrc);
            if (matcher.find()) {
                System.out.println(imgSrc);
                DownloadUtil.get(imgSrc,"douban");
            }
        }
    }

    public static void main(String[] args) {
        DoubanGetter getter = new DoubanGetter();
        getter.getDouban();
//        String result = HttpClientFactory.invokeGet("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2398173791.jpg");
//        System.out.println(result);

    }
}
