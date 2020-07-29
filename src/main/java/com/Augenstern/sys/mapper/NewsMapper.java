package com.Augenstern.sys.mapper;

import com.Augenstern.sys.domain.News;
import java.util.List;

import com.Augenstern.sys.vo.NewsVo;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertOrUpdate(News record);

    int insertOrUpdateSelective(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    int updateBatch(List<News> list);

    int updateBatchSelective(List<News> list);

    int batchInsert(@Param("list") List<News> list);

    /**
     * 查询所有
     */
    List<News> queryAllNews(News news);
}