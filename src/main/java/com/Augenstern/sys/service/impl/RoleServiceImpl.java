package com.Augenstern.sys.service.impl;

import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.sys.domain.Menu;
import com.Augenstern.sys.domain.Role;
import com.Augenstern.sys.mapper.MenuMapper;
import com.Augenstern.sys.mapper.RoleMapper;
import com.Augenstern.sys.service.RoleService;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.utils.TreeNode;
import com.Augenstern.sys.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有角色
     */
    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * 根据用户id查询用户的可用角色
     */
    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * 查询所有角色
     */
    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> data = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加角色
     */
    @Override
    public void addRole(RoleVo roleVo) {
        this.roleMapper.insertSelective(roleVo);
    }

    /**
     * 修改角色
     */
    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    /**
     * 根据id删除角色
     */
    @Override
    public void deleteRole(Integer roleid) {
        // 删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        // 根据角色id删除 sys_role_menu 里的数据
        this.roleMapper.deleteRoleMenuByRid(roleid);
        // 根据角色id删除 sys_role_user 里的数据
        this.roleMapper.deleteRoleUserByRid(roleid);
    }

    /**
     * 批量删除角色
     */
    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRole(rid);
        }
    }

    /**
     * 加载角色管理分配菜单的json
     */
    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        // 根据 id 查询当前角色永远的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstant.AVAILABLE_TRUE,roleid);
        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstant.CODE_ZERO + "";
            for (Menu m2 : roleMenu) {
                if(m1.getId() == m2.getId()) {
                    checkArr = SysConstant.CODE_ONE + "";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread()==SysConstant.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id,pid,title,spread,checkArr));
        }
        return new DataGridView(data);
    }

    /**
     * 保存角色和菜单的关系
     */
    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid = roleVo.getRoleid();
        Integer[] mids = roleVo.getIds();
        // 根据 rid 删除 sys_role_menu 里面的所有数据
        this.roleMapper.deleteRoleMenuByRid(rid);
        // 保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
