package com.Augenstern.bus.service.impl;

import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.bus.vo.CarVo;
import com.Augenstern.sys.utils.AppFileUtils;
import com.Augenstern.sys.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.Augenstern.bus.domain.Car;
import com.Augenstern.bus.mapper.CarMapper;
import com.Augenstern.bus.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Resource
    private CarMapper carMapper;

    /**
     * 查询所有信息
     */
    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(),carVo.getLimit());
        List<Car> data = this.carMapper.queryAllCar(carVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加一个车辆
     */
    @Override
    public void addCar(CarVo carVo) {
        this.carMapper.insertSelective(carVo);
    }

    /**
     * 更新一个车辆
     */
    @Override
    public void updateCar(CarVo carVo) {
        this.carMapper.updateByPrimaryKeySelective(carVo);
    }

    /**
     * 根据id删除车辆
     */
    @Override
    public void deleteCar(String carnumber) {
        //先删除图片
        Car car = this.carMapper.selectByPrimaryKey(carnumber);
        //如果不是默认图片就删除
        if (!car.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)){
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        //删除数据库的数据
        this.carMapper.deleteByPrimaryKey(carnumber);
    }

    /**
     * 批量删除车辆
     * @param carnumbers
     */
    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers) {
            this.deleteCar(carnumber);
        }
    }

    /**
     * 根据车牌号查询
     */
    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return this.carMapper.selectByPrimaryKey(carnumber);
    }
}
