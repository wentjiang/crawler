package com.wentjiang.crawler.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class Test {
    public static void main(String[] args) {
//        String str = "https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2395733377.jpg";
//        String regex = "https://img\\d.doubanio.com/view/movie_poster_cover/lpst/public/\\w*";
//
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//        System.out.println(matcher.find());
        Pattern idPattern = Pattern.compile("\\d+");
        String url = "https://book.douban.com/subject/26872990/";
        Matcher matcher = idPattern.matcher(url);
        if (matcher.find()){
            System.out.println(matcher.group());
        }else{
            System.out.println("not find");
        }
    }
}
