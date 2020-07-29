package com.Augenstern.sys.mapper;

import com.Augenstern.sys.domain.Menu;
import com.Augenstern.sys.domain.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     * 根据pid查询菜单数量
     */
    Integer queryMenuByPid(@Param("pid")Integer pid);

    /**
     * 根据 菜单ID 删除 sys_role_menu 里面的数据
     */
    void deleteRoleMenuByMid(@Param("mid")Integer mid);

    /**
     * 根据角色id查询菜单
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer available, @Param("rid") Integer rid);

    /**
     * 根据用户id查询菜单
     */
    List<Menu> queryMenuByUid(@Param("available")Integer available, @Param("uid")Integer userId);
}