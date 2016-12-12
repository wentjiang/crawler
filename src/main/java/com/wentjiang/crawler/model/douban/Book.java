package com.wentjiang.crawler.model.douban;

public class Book {
    private Long id;

    private String name;

    private String author;

    private String publishingHouse;

    private String publishDate;

    private Float averageStar;

    private Float start1;

    private Float start2;

    private Float start3;

    private Float start4;

    private Float start5;

    private Integer commentNum;

    private String url;

    private String describe;

    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse == null ? null : publishingHouse.trim();
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate == null ? null : publishDate.trim();
    }

    public Float getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(Float averageStar) {
        this.averageStar = averageStar;
    }

    public Float getStart1() {
        return start1;
    }

    public void setStart1(Float start1) {
        this.start1 = start1;
    }

    public Float getStart2() {
        return start2;
    }

    public void setStart2(Float start2) {
        this.start2 = start2;
    }

    public Float getStart3() {
        return start3;
    }

    public void setStart3(Float start3) {
        this.start3 = start3;
    }

    public Float getStart4() {
        return start4;
    }

    public void setStart4(Float start4) {
        this.start4 = start4;
    }

    public Float getStart5() {
        return start5;
    }

    public void setStart5(Float start5) {
        this.start5 = start5;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}