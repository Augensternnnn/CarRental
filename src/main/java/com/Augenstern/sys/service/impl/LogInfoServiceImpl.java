package com.Augenstern.sys.service.impl;

import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.LogInfoVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.Augenstern.sys.domain.LogInfo;
import com.Augenstern.sys.mapper.LogInfoMapper;
import com.Augenstern.sys.service.LogInfoService;

import java.util.List;

/**
 * 日志管理
 */
@Service
public class LogInfoServiceImpl implements LogInfoService{
    @Resource
    private LogInfoMapper logInfoMapper;

    /**
     * 查询所有日志
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加日志
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        this.logInfoMapper.insertSelective(logInfoVo);
    }

    /**
     * 根据id删除日志
     */
    @Override
    public void deleteLogInfo(Integer logInfoid) {
        this.logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    /**
     * 批量删除日志
     */
    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteLogInfo(id);
        }
    }
}
