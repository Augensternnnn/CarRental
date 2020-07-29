package com.Augenstern.bus.service;

import com.Augenstern.bus.domain.Rent;
import com.Augenstern.bus.vo.RentVo;
import com.Augenstern.sys.utils.DataGridView;

public interface RentService{
    /**
     * 保存出租单信息
     */
    void addRent(RentVo rentVo);

    /**
     * 查询
     */
    DataGridView queryAllRent(RentVo rentVo);

    /**
     * 修改出租单
     */
    void updateRent(RentVo rentVo);

    /**
     * 删除出租单
     */
    void deleteRent(String rentid);

    /**
     * 根据出租单号查询出租单信息
     */
    Rent queryRentByRentId(String rentid);
}
