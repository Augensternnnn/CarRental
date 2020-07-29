package com.Augenstern.sys.service;

import com.Augenstern.sys.domain.Role;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.RoleVo;

import java.util.List;

/**
 * 角色管理的服务接口
 */
public interface RoleService {
    /**
     * 查询所有角色
     */
    List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id查询用户的可用角色
     */
    List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);

    /**
     * 查询所有角色
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     */
    void deleteBatchRole(Integer[] ids);

    /**
     * 加载角色管理分配菜单的json
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存角色和菜单的关系
     */
    void saveRoleMenu(RoleVo roleVo);
}
