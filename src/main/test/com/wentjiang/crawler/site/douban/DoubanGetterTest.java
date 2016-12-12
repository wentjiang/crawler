package com.wentjiang.crawler.site.douban;

import com.wentjiang.crawler.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jiangwentao on 12/9/2016 3:55 PM.
 */
public class DoubanGetterTest extends BaseTest {
    @Autowired
    private DoubanGetter doubanGetter;

    @Test
    public void getDoubanbook() throws Exception {
        doubanGetter.getDoubanbook();
    }

}