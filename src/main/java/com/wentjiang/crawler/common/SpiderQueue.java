package com.wentjiang.crawler.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiangwentao on 12/5/2016 2:12 PM.
 */
public class SpiderQueue {
    /**
     * 待访问的url集合，即unVisited表
     */
    private Queue unVisitedUrl = new Queue();
    /**
     * 已访问的url集合，即Visited表
     */
    private Set<Object> visitedUrl = new HashSet<>();

    /**
     * 添加到访问过的 URL 队列中
     */
    public void addVisitedUrl(String url) {
        visitedUrl.add(url);
    }

    /**
     * 移除访问过的 URL
     */
    public void removeVisitedUrl(String url) {
        visitedUrl.remove(url);
    }

    /**
     * 获得已经访问的 URL 数目
     */
    public int getVisitedUrlNum() {
        return visitedUrl.size();
    }

    /**
     * 获得UnVisited队列
     */
    public Queue getUnVisitedUrl() {
        return unVisitedUrl;
    }

    /**
     * 未访问的unVisitedUrl出队列
     */
    public String unVisitedUrlDeQueue() {
        return unVisitedUrl.deQueue();
    }

    /**
     * 保证添加url到unVisitedUrl的时候每个 URL只被访问一次
     */
    public void addUnvisitedUrl(String url) {
        if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
                && !unVisitedUrl.contians(url))
            unVisitedUrl.enQueue(url);
    }

    /**
     * 判断未访问的 URL队列中是否为空
     */
    public boolean unVisitedUrlsEmpty() {
        return unVisitedUrl.empty();
    }
}
