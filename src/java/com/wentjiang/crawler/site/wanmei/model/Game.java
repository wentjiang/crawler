package com.wentjiang.crawler.site.wanmei.model;

/**
 * Created by jiangwentao on 12/5/2016 10:56 AM.
 */
public class Game {
    private String name;
    private String url;
    private boolean isNew;
    private boolean isHot;

    public Game() {
    }

    public Game(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public String toString() {
        return "name: " + name + " url:" + url + " isHot:" + isHot + " isNew:" + isNew;
    }
}
