package com.wentjiang.crawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class RegexStringUtil {
    public static String regexString(String targetStr,String patternStr){
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(targetStr);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
