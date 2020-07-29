package com.Augenstern.bus.mapper;

import com.Augenstern.bus.domain.Car;
import com.Augenstern.bus.vo.CarVo;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carnumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    /**
     * 查询所有车辆
     */
    List<Car> queryAllCar(CarVo carVo);
}