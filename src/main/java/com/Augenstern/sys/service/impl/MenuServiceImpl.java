package com.Augenstern.sys.service.impl;

import com.Augenstern.sys.domain.Menu;
import com.Augenstern.sys.mapper.MenuMapper;
import com.Augenstern.sys.service.MenuService;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.MenuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单
     */
    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    /**
     * 根据用户id查询用户的可用菜单
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryMenuByUid(menuVo.getAvailable(), userId);
    }

    /**
     * 查询所有菜单
     */
    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加菜单
     */
    @Override
    public void addMenu(MenuVo menuVo) {
        this.menuMapper.insertSelective(menuVo);
    }

    /**
     * 修改菜单
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 根据pid查询菜单数量
     */
    @Override
    public Integer queryMenuByPid(Integer pid) {
        return this.menuMapper.queryMenuByPid(pid);
    }

    /**
     * 根据id删除菜单
     */
    @Override
    public void deleteMenu(MenuVo menuVo) {
        // 删除菜单表的数据
        this.menuMapper.deleteByPrimaryKey(menuVo.getId());
        // 根据 菜单ID 删除 sys_role_menu 里面的数据
        this.menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }
}
