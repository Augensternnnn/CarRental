package com.Augenstern.sys.service;

import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.LogInfoVo;

/**
 * 日志管理的服务接口
 */
public interface LogInfoService{
    /**
     * 查询所有日志
     */
    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 添加日志
     */
    void addLogInfo(LogInfoVo logInfoVo);

    /**
     * 根据id删除日志
     */
    void deleteLogInfo(Integer logInfoid);

    /**
     * 批量删除日志
     */
    void deleteBatchLogInfo(Integer [] ids);
}
