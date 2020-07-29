package com.Augenstern.bus.service;

import java.util.List;
import com.Augenstern.bus.domain.Customer;
import com.Augenstern.bus.vo.CustomerVo;
import com.Augenstern.sys.utils.DataGridView;

public interface CustomerService{
    /**
     * 查询所有客户
     */
    DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 添加客户
     */
    void addCustomer(CustomerVo customerVo);

    /**
     * 修改客户
     */
     void updateCustomer(CustomerVo customerVo);

    /**
     * 根据id删除客户
     */
    void deleteCustomer(String identity);

    /**
     * 批量删除客户
     */
    void deleteBatchCustomer(String[] identitys);

    /**
     * 根据身份号查询客户信息
     */
    Customer queryCustomerByIdentity(String identity);

    /**
     * 查询客户数据返回集合
     */
    List<Customer> queryAllCustomerForList(CustomerVo customerVo);
}
