package com.wentjiang.crawler.mapper.douban;

import com.wentjiang.crawler.model.douban.BookBookTagExample;
import com.wentjiang.crawler.model.douban.BookBookTagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookBookTagMapper {
    int countByExample(BookBookTagExample example);

    int deleteByExample(BookBookTagExample example);

    int deleteByPrimaryKey(BookBookTagKey key);

    int insert(BookBookTagKey record);

    int insertSelective(BookBookTagKey record);

    List<BookBookTagKey> selectByExample(BookBookTagExample example);

    int updateByExampleSelective(@Param("record") BookBookTagKey record, @Param("example") BookBookTagExample example);

    int updateByExample(@Param("record") BookBookTagKey record, @Param("example") BookBookTagExample example);
}