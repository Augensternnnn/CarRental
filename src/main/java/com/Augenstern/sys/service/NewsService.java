package com.Augenstern.sys.service;

import com.Augenstern.sys.domain.News;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.NewsVo;

public interface NewsService{
    /**
     * 查询所有公告
     */
    DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 添加公告
     */
    void addNews(NewsVo newsVo);

    /**
     * 删除公告
     */
    void deleteNews(Integer newsid);

    /**
     * 批量删除公告
     */
    void deleteBatchNews(Integer[] ids);

    /**
     * 修改公告
     */
    void updateNews(NewsVo newsVo);

    /**
     * 通过id查询一条公告
     */
    News queryNewsById(Integer id);
}
