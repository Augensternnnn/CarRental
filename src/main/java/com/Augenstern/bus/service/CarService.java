package com.Augenstern.bus.service;

import com.Augenstern.bus.domain.Car;
import com.Augenstern.bus.vo.CarVo;
import com.Augenstern.sys.utils.DataGridView;

/**
 * 车辆管理的服务接口
 */
public interface CarService{
    /**
     * 查询所有车辆
     */
    DataGridView queryAllCar(CarVo carVo);

    /**
     * 添加车辆
     */
    void addCar(CarVo carVo);

    /**
     * 修改车辆
     */
    void updateCar(CarVo carVo);

    /**
     * 根据id删除车辆
     */
    void deleteCar(String carnumber);

    /**
     * 批量删除车辆
     */
    void deleteBatchCar(String[] carnumbers);

    /**
     * 根据车牌号查询
     */
    Car queryCarByCarNumber(String carnumber);
}
