package com.Augenstern.stat.controller;

import com.Augenstern.bus.domain.Customer;
import com.Augenstern.bus.domain.Rent;
import com.Augenstern.bus.service.CustomerService;
import com.Augenstern.bus.service.RentService;
import com.Augenstern.bus.vo.CustomerVo;
import com.Augenstern.stat.domain.BaseEntity;
import com.Augenstern.stat.service.StatService;
import com.Augenstern.stat.utils.ExportCustomerUtils;
import com.Augenstern.stat.utils.ExportRentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
@Controller
@RequestMapping("stat")
public class statController {
    @Autowired
    private StatService statService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentService rentService;

    /**
     * 跳转到客户地区统计页面
     */
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     */
    @RequestMapping("loadCustomerAreaStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJson() {
        return this.statService.loadCustomerAreaStatList();
    }

    /**
     * 跳转到客户地区性别统计页面
     */
    @RequestMapping("toCustomerAreaSexStat")
    public String toCustomerAreaSexStat(){
        return "stat/customerAreaSexStat";
    }

    /**
     * 加载客户地区性别统计数据
     */
    @RequestMapping("loadCustomerAreaSexStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaSexStatJson(String area) {
        return this.statService.loadCustomerAreaSexStatList(area);
    }

    /**
     * 跳转到业务员年度业务统计
     */
    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat(){
        return "stat/opernameYearGradeStat";
    }

    /**
     * 加载业务员年度业务统计数据
     */
    @RequestMapping("loadOpernameYearGradeStatJson")
    @ResponseBody
    public Map<String,Object> loadOpernameYearGradeStatJson(String year) {
        List<BaseEntity> entities = this.statService.loadOpernameYearGradeStatList(year);
        Map<String,Object> map = new HashMap<>();
        List<String> names = new ArrayList<String >();
        List<Double> values = new ArrayList<Double>();
        for (BaseEntity baseEntity : entities) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }
        map.put("name",names);
        map.put("values",values);
        return map;

    }

    /**
     * 跳转到公司年度业务统计
     */
    @RequestMapping("toCompanyYearGradeStat")
    public String toCompanyYearGradeStat(){
        return "stat/companyYearGradeStat";
    }

    /**
     * 加载公司年度业务统计数据
     */
    @RequestMapping("loadCompanyYearGradeStatJson")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStatJson(String year){
        List<Double> list = this.statService.loadCompanyYearGradeStatList(year);
        for (int i = 0; i < list.size(); i++) {
            if(null == list.get(i))
                list.set(i, 0.0);
        }
        return list;
    }

    /**
     * 导出客户数据
     */
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response){
        List<Customer> customers = customerService.queryAllCustomerForList(customerVo);
        String fileName="客户数据.xls";
        String sheetName="客户数据";
        ByteArrayOutputStream bos = ExportCustomerUtils.exportCustomer(customers,sheetName);
        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建 封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出出租单数据
     */
    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid){
        //根据出租单号查询出租单信息
        Rent rent = rentService.queryRentByRentId(rentid);
        //根据身份证号查询客户信息
        Customer customer = customerService.queryCustomerByIdentity(rent.getIdentity());

        String fileName=customer.getCustname()+"-的出租单.xls";
        String sheetName=customer.getCustname()+"出租单";

        ByteArrayOutputStream bos = ExportRentUtils.exportRent(rent,customer,sheetName);

        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建 封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
