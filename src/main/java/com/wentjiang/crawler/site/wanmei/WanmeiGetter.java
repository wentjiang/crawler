package com.wentjiang.crawler.site.wanmei;

import com.wentjiang.crawler.http.HttpClientFactory;
import com.wentjiang.crawler.util.DownloadUtil;
import com.wentjiang.crawler.util.HttpUtil;
import com.wentjiang.crawler.site.wanmei.model.Game;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by jiangwentao on 12/5/2016 10:45 AM.
 */
public class WanmeiGetter {
    public void getWanmeiGames() {
        String url = "http://www.wanmei.com/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements gameBoxes = doc.getElementsByClass("game_box");
            for (Element gameBox : gameBoxes) {
                Elements games = gameBox.getElementsByTag("a");
                for (Element gameEle : games) {
                    Game game = new Game();
                    String gameWebUrl = gameEle.attr("href");
                    String gameName = gameEle.text();
                    if (gameEle.getElementsByClass("new").size() > 0) {
                        game.setNew(true);
                    }
                    if (gameEle.getElementsByClass("hot").size() > 0) {
                        game.setHot(true);
                    }
                    game.setUrl(gameWebUrl);
                    game.setName(gameName);
                    System.out.println(game.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getZhuxianGamewall() {
        String url = "http://zhuxian.wanmei.com/gamewall/list.shtml";
        int index = url.lastIndexOf("/");
        String baseUrl = url.substring(0, index);
        //            Document doc = Jsoup.connect(url).get();
        String html = HttpClientFactory.invokeGet(url);
        Document doc = Jsoup.parse(html);
//        doc.setBaseUri("http://zhuxian.wanmei.com/gamewall");
        Elements pages = doc.getElementsByClass("pages");
        Element page = pages.get(0);
        Elements as = page.getElementsByTag("a");
        //获取当前页面的图片并下载
//            downloadGamewall(doc);
        for (Element a : as) {
            //获取所有的a
            String wallpageRelativeUrl = a.attr("href");
            String wallpageUrl = baseUrl + "/" + wallpageRelativeUrl;
            System.out.println("start download page : " + wallpageUrl);
            try {
                Document pageDoc = Jsoup.connect(wallpageUrl).get();
                downloadGamewall(pageDoc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getDota2Gamewall(){
        String url = "http://www.dota2.com.cn/enjoy/gamewall/index.htm";
        String html = HttpUtil.httpGet(url);
        String baseUri = "http://www.dota2.com.cn";
        Document doc = Jsoup.parse(html,baseUri);
        Element page = doc.getElementById("btn_more");
        Elements as = page.getElementsByTag("a");
        //获取当前页面的图片并下载
        downloadGamewall(doc);
        for (Element a : as) {
            //获取所有的a
            String wallpageUrl = a.attr("abs:href");
            System.out.println(wallpageUrl);
            if (wallpageUrl.matches("^\\s*(http|https)\\://[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9.-_\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*\\s*$")){
                System.out.println("start download page : " + wallpageUrl);
                try {
                    Document pageDoc = Jsoup.connect(wallpageUrl).get();
                    downloadGamewall(pageDoc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void downloadGamewall(Document page) {
        Elements as = page.getElementsByTag("a");
        for (Element a : as) {
            String wallUrl = a.attr("href");
            if ((a.text().equals("1920x1080")||a.text().equals("1920x1200")) && !StringUtil.isBlank(wallUrl)) {
                //执行下载逻辑
                try {
                    DownloadUtil.get(wallUrl,"zxwall");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        WanmeiGetter getter = new WanmeiGetter();
        getter.getZhuxianGamewall();
    }

}
