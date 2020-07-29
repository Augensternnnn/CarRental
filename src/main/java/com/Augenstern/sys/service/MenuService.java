package com.Augenstern.sys.service;

import com.Augenstern.sys.domain.Menu;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理的服务接口
 */
public interface MenuService {
    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有菜单
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     */
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 根据pid查询菜单数量
     */
    Integer queryMenuByPid(@Param("pid") Integer pid);

    /**
     * 根据id删除菜单
     */
    void deleteMenu(MenuVo menuVo);
}
