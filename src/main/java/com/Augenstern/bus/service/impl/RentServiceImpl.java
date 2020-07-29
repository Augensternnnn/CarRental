package com.Augenstern.bus.service.impl;

import com.Augenstern.bus.domain.Car;
import com.Augenstern.bus.mapper.CarMapper;
import com.Augenstern.bus.vo.RentVo;
import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.sys.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.Augenstern.bus.domain.Rent;
import com.Augenstern.bus.mapper.RentMapper;
import com.Augenstern.bus.service.RentService;

import java.util.List;

@Service
public class RentServiceImpl implements RentService{
    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 保存出租单信息
     */
    @Override
    public void addRent(RentVo rentVo) {
        this.rentMapper.insertSelective(rentVo);
        // 更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstant.RENT_CAR_TRUE);    // 设置车辆为已出租状态
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询
     */
    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        List<Rent> data = this.rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 修改出租单
     */
    @Override
    public void updateRent(RentVo rentVo) {
        this.rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    /**
     * 删除出租单
     */
    @Override
    public void deleteRent(String rentid) {
        // 更改出租状态
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        // 转换成未出租的状态
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        // 删除出租单
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    /**
     * 根据出租单号查询出租单信息
     */
    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }
}
