package com.wentjiang.crawler.mapper.douban;

import com.wentjiang.crawler.model.douban.BookTag;
import com.wentjiang.crawler.model.douban.BookTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookTagMapper {
    int countByExample(BookTagExample example);

    int deleteByExample(BookTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BookTag record);

    int insertSelective(BookTag record);

    List<BookTag> selectByExampleWithBLOBs(BookTagExample example);

    List<BookTag> selectByExample(BookTagExample example);

    BookTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BookTag record, @Param("example") BookTagExample example);

    int updateByExampleWithBLOBs(@Param("record") BookTag record, @Param("example") BookTagExample example);

    int updateByExample(@Param("record") BookTag record, @Param("example") BookTagExample example);

    int updateByPrimaryKeySelective(BookTag record);

    int updateByPrimaryKeyWithBLOBs(BookTag record);

    int updateByPrimaryKey(BookTag record);
}