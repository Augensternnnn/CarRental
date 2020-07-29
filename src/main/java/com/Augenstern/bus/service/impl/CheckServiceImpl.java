package com.Augenstern.bus.service.impl;

import com.Augenstern.bus.domain.Car;
import com.Augenstern.bus.domain.Customer;
import com.Augenstern.bus.domain.Rent;
import com.Augenstern.bus.mapper.CarMapper;
import com.Augenstern.bus.mapper.CustomerMapper;
import com.Augenstern.bus.mapper.RentMapper;
import com.Augenstern.bus.vo.CheckVo;
import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.sys.domain.User;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.utils.RandomUtils;
import com.Augenstern.sys.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import javafx.css.CssMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.Augenstern.bus.mapper.CheckMapper;
import com.Augenstern.bus.domain.Check;
import com.Augenstern.bus.service.CheckService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService{
    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 根据出租单号加载检查单的表单数据
     */
    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        // 查询出租单
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        // 查询客户
        Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        // 查询车辆
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        // 组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user = (User)WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String,Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);
        return map;
    }

    /**
     * 保存检查单数据
     */
    @Override
    public void addCheck(CheckVo checkVo) {
        this.checkMapper.insertSelective(checkVo);
        // 更改出租单状态
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        // 更改为已归还
        rent.setRentflag(SysConstant.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        // 更改汽车状态
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 批量删除检查单
     */
    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids) {
            this.checkMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 删除检查单
     */
    @Override
    public void deleteCheck(CheckVo checkVo) {
        this.checkMapper.deleteByPrimaryKey(checkVo.getCheckid());
    }

    /**
     * 更新检查单
     */
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}
