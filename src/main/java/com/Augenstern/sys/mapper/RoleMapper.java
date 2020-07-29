package com.Augenstern.sys.mapper;

import com.Augenstern.sys.domain.Role;
import com.Augenstern.sys.domain.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色
     */
    List<Role> queryAllRole(Role role);

    /**
     * 根据角色id删除 sys_role_menu 里的数据
     */
    void deleteRoleMenuByRid(Integer roleid);

    /**
     * 根据角色id删除 sys_role_user 里的数据
     */
    void deleteRoleUserByRid(Integer roleid);

    /**
     * 保存角色和菜单之间的关系
     */
    void insertRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 根据用户id删除 sys_role_user 里面的数据
     */
    void deleteRoleUserByUid(Integer userid);

    /**
     * 根据用户id查询已拥有的角色
     */
    List<Role> queryRoleByUid(@Param("available") Integer availableTrue, @Param("uid") Integer userid);
}