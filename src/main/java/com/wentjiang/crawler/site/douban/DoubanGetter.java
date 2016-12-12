package com.wentjiang.crawler.site.douban;

import com.wentjiang.crawler.common.SpiderQueue;
import com.wentjiang.crawler.http.HttpClientFactory;
import com.wentjiang.crawler.mapper.douban.BookMapper;
import com.wentjiang.crawler.model.douban.Book;
import com.wentjiang.crawler.util.DownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wentjiang.crawler.http.HttpClientFactory.invokeGet;

/**
 * Created by jiangwentao on 12/5/2016 10:56 AM.
 */
@Slf4j
@Component
public class DoubanGetter {
    @Autowired
    private BookMapper bookMapper;
    private SpiderQueue spiderQueue = new SpiderQueue();
    private static Pattern urlPattern = Pattern.compile("https://book.douban.com/subject/\\d*");
    private static Pattern idPattern = Pattern.compile("\\d+");

    public void getDoubanbook() {


        doCrawler();
//        String indexPage = HttpClientFactory.invokeGet(url);
//        Document doc = Jsoup.parse(indexPage);

    }

    private void doCrawler() {
        String baseUrl = "https://book.douban.com/";
        spiderQueue.addUnvisitedUrl(baseUrl);
        while (!spiderQueue.getUnVisitedUrl().empty()) {

            //获取未访问的链接
            String url = spiderQueue.unVisitedUrlDeQueue();
            log.info("start get url :" + url );
            String bookId = null;
            Matcher matcher = idPattern.matcher(url);
            if (matcher.find()) {
                bookId = (matcher.group().trim());
            }
            try {
                //请求页面信息
                String html = HttpClientFactory.invokeGet(url);
                Document doc = Jsoup.parse(html, "UTF-8");
                List<String> urls = getBookUrl(doc);
                for (String addUrl : urls) {
                    spiderQueue.addUnvisitedUrl(addUrl);
                }
                if (url.equals(baseUrl)) {
                    continue;
                }


                Book book = doParse(doc, url);
                if (bookId != null) {
                    book.setBookId(Long.parseLong(bookId));
                }
                book.setUrl(url);
                //数据添加到数据库
                bookMapper.insert(book);
                log.info("get book " + book.getName() + " success");
            }catch (Exception e){
                log.warn("url:" + url + " error");
                e.printStackTrace();
            }

            //添加链接到访问过
            spiderQueue.addVisitedUrl(url);
        }
    }

    //处理页面中关于图书的信息
    private Book doParse(Document doc, String url) {
        log.info("");
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
        if (stars.size() > 5) {
            String star5 = stars.get(0).text().trim();
            String star4 = stars.get(1).text().trim();
            String star3 = stars.get(2).text().trim();
            String star2 = stars.get(3).text().trim();
            String star1 = stars.get(4).text().trim();
            book.setStart1(Float.parseFloat(star1.replace("%", "")));
            book.setStart2(Float.parseFloat(star2.replace("%", "")));
            book.setStart3(Float.parseFloat(star3.replace("%", "")));
            book.setStart4(Float.parseFloat(star4.replace("%", "")));
            book.setStart5(Float.parseFloat(star5.replace("%", "")));
        }

        String commentNum = doc.select(".rating_people span").first().text().trim();

        book.setName(name);
        book.setAuthor(author);
        book.setAuthor(author);
        book.setPublishingHouse(publishingHouse);
        book.setPublishDate(date);
        book.setAverageStar(Float.parseFloat(averageStar));

        book.setCommentNum(Integer.parseInt(commentNum));

        return book;
    }

    //查找页面中的图书链接
    public List<String> getBookUrl(Document doc) {
        List<String> bookUrls = new LinkedList<>();
        Elements taga = doc.getElementsByTag("a");
        if (taga != null && taga.size() > 0) {
            for (Element a : taga) {
                String url = a.attr("href");
                if (url.matches("https://book.douban.com/subject.*")) {
                    Matcher matcher = urlPattern.matcher(url);
                    if (matcher.find()) {
                        bookUrls.add(matcher.group());
                    }
                }
            }
        }
        return bookUrls;
    }


    public void getDouban() {
        //爬去douban电影海报
        String url = "https://movie.douban.com/";
        String regex = "https://img\\d.doubanio.com/view/movie_poster_cover/lpst/public/\\w*";
        String html = invokeGet(url);
        Document doc = Jsoup.parse(html);
        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            String imgSrc = img.attr("src");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(imgSrc);
            if (matcher.find()) {
                System.out.println(imgSrc);
                DownloadUtil.get(imgSrc, "douban");
            }
        }
    }

    public static void main(String[] args) {
        DoubanGetter getter = new DoubanGetter();
        getter.getDoubanbook();
//        String result = HttpClientFactory.invokeGet("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2398173791.jpg");
//        System.out.println(result);

    }
}
