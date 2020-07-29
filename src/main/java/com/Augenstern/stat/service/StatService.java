package com.Augenstern.stat.service;

import com.Augenstern.stat.domain.BaseEntity;

import java.util.List;

/**
 * 统计分析的数据服务接口
 */
public interface StatService {
    /**
     * 查询客户地区数据
     */
    List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 查询客户地区性别数据
     */
    List<BaseEntity> loadCustomerAreaSexStatList(String area);

    /**
     * 业务员年度销售额数据
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);

    /**
     * 公司年度月份销售数据
     */
    List<Double> loadCompanyYearGradeStatList(String year);
}
