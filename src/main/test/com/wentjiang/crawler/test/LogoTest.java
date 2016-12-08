package com.wentjiang.crawler.test;

import com.wentjiang.crawler.util.RegexStringUtil;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class LogoTest {
    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        String regex = "hidefocus.+?src=\"//(.+?)\"";
        System.out.println(regex);
//        String result = HttpUtil.get(url);
        String result = "<img hidefocus=\"true\" src=\"//www.baidu.com/img/bd_logo1.png\" width=\"270\" height=\"129\">";
        System.out.println("result = "+result);
        String src = RegexStringUtil.regexString(result,regex);
        System.out.println("src = " + src);
    }
}
