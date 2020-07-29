package com.Augenstern.sys.utils;



import com.Augenstern.sys.domain.Menu;
import com.Augenstern.sys.mapper.MenuMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * 初始化菜单数据
 * @author LJH
 *
 */
public class TestDataSource {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		MenuMapper menuMapper = context.getBean(MenuMapper.class);
		List<Menu> queryAllMenu = menuMapper.queryAllMenu(new Menu());
		System.out.println(queryAllMenu.size());
		System.out.println(menuMapper);
		System.out.println("初始化完成");
		
	}
}
