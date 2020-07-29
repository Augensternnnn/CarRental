package com.Augenstern.bus.service;

import com.Augenstern.bus.domain.Check;
import com.Augenstern.bus.vo.CheckVo;
import com.Augenstern.sys.utils.DataGridView;

import java.util.Map;

/**
 * 检查单服务管理的接口
 */
public interface CheckService{
    /**
     * 根据出租单号加载检查单的表单数据
     */
    Map<String,Object> initCheckFormData(String rentid);

    /**
     * 保存检查单数据
     */
    void addCheck(CheckVo checkVo);

    /**
     * 查询所有检查单
     */
    DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 批量删除检查单
     */
    void deleteBatchCheck(String[] ids);

    /**
     * 删除检查单
     */
    void deleteCheck(CheckVo checkVo);

    /**
     * 更新检查单
     */
    void updateCheck(CheckVo checkVo);
}
