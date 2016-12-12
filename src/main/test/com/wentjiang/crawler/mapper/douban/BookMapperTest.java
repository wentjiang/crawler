package com.wentjiang.crawler.mapper.douban;

import com.wentjiang.crawler.BaseTest;
import com.wentjiang.crawler.model.douban.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jiangwentao on 12/9/2016 4:13 PM.
 */
public class BookMapperTest extends BaseTest{
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void insert() throws Exception {
        Book book = new Book();
        book.setName("ceshi ");
        book.setAuthor("jwt");
        bookMapper.insert(book);
    }

}