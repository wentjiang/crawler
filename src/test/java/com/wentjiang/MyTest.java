package com.wentjiang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class MyTest {

    public static void main(String args[]) {
        String regexStr = "[abdh]e";
        String targetStr = "hello worlhed";
        //获取Pattern对象
        Pattern pattern = Pattern.compile(regexStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
