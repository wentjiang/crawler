package com.wentjiang.crawler;

import com.wentjiang.crawler.util.DownloadUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentjiang on 2016/12/3.
 * 主启动程序
 */
public class Main {
    public static void main(String[] args) {
        //爬去douban电影海报
        String url ="https://movie.douban.com/";
        String regex = "https://img\\d.doubanio.com/view/movie_poster_cover/lpst/public/\\w*";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements imgs = doc.getElementsByTag("img");
            for (Element img : imgs){
                String imgSrc = img.attr("src");
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(imgSrc);
                if (matcher.find()){
                    System.out.println(imgSrc);
                    DownloadUtil.get(imgSrc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
