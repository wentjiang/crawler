package com.wentjiang.crawler.model.douban;

import lombok.Data;

/**
 * Created by jiangwentao on 12/6/2016 2:03 PM.
 */
@Data
public class DoubanBook {
    private int id;
    private String name;
    private String author;
    private String publishingHouse;
    private String publishDate;
    private float averageStar;
    private float star1;
    private float star2;
    private float star3;
    private float star4;
    private float star5;
    private int commentPeople;
    private String url;
}
