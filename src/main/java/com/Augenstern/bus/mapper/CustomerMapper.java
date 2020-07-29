package com.Augenstern.bus.mapper;

import com.Augenstern.bus.domain.Customer;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertOrUpdate(Customer record);

    int insertOrUpdateSelective(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    int updateBatch(List<Customer> list);

    int updateBatchSelective(List<Customer> list);

    int batchInsert(@Param("list") List<Customer> list);

    List<Customer> queryAllCustomer(Customer customer);
}