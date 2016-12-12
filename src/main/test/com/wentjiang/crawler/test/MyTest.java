package com.wentjiang.crawler.test;

import com.wentjiang.crawler.http.HttpClientFactory;
import com.wentjiang.crawler.model.douban.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wentjiang on 2016/12/3.
 */
public class MyTest {

    public static void main(String args[]) {
        Pattern idPattern = Pattern.compile("\\d+");
        String url = "https://book.douban.com/subject/26872990/";
        String url2 = "https://book.douban.com/";
        String html = HttpClientFactory.invokeGet(url);
        Document doc = Jsoup.parse(html, "utf-8");
        Book book = new Book();
        String name = doc.select("h1 span").first().text().trim();
        Element info = doc.getElementById("info");
        String author = info.select("span a").first().text().trim();
        String publishingHouse = null;
        String date = null;
        for (Node child : info.childNodes()) {
            if (child instanceof TextNode) {
                if (((TextNode) child).text().matches("\\d{4}-\\d{2}")) {
                    date = ((TextNode) child).text();
                }
            }
        }
        String averageStar = doc.getElementById("interest_sectl").getElementsByTag("strong").text().trim();
        Elements stars = doc.select(".rating_per");
        String star5 = stars.get(0).text().trim();
        String star4 = stars.get(1).text().trim();
        String star3 = stars.get(2).text().trim();
        String star2 = stars.get(3).text().trim();
        String star1 = stars.get(4).text().trim();
        String commentNum = doc.select(".rating_people span").first().text().trim();
        String bookId = null;
        Matcher matcher = idPattern.matcher(url);
        if (matcher.find()) {
            bookId = (matcher.group().trim());
        }
        book.setName(name);
        book.setAuthor(author);
        book.setAuthor(author);
        book.setPublishingHouse(publishingHouse);
        book.setPublishDate(date);
        book.setAverageStar(Float.parseFloat(averageStar));
        book.setStart1(Float.parseFloat(star1.replace("%", "")));
        book.setStart2(Float.parseFloat(star2.replace("%", "")));
        book.setStart3(Float.parseFloat(star3.replace("%", "")));
        book.setStart4(Float.parseFloat(star4.replace("%", "")));
        book.setStart5(Float.parseFloat(star5.replace("%", "")));
        book.setCommentNum(Integer.parseInt(commentNum));
        if (bookId != null) {
            book.setBookId(Long.parseLong(bookId));
        }
    }
}
