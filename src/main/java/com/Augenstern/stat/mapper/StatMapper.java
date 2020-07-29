package com.Augenstern.stat.mapper;

import com.Augenstern.stat.domain.BaseEntity;

import java.util.List;

public interface StatMapper {
    /**
     * 查询客户地区数据
     */
    List<BaseEntity> queryCustomerAreaStat();

    /**
     * 查询客户地区性别数据
     */
    List<BaseEntity> queryCustomerAreaSexStat(String area);

    /**
     * 查询业务员年度业绩
     */
    List<BaseEntity> queryOpernameYearGradeStat(String year);

    /**
     * 查询公司年度业务销售额数据
     */
    List<Double> queryCompanyYearGradeStat(String year);
}
