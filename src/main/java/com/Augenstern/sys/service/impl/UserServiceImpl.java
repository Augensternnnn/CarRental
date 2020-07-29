package com.Augenstern.sys.service.impl;

import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.sys.domain.Role;
import com.Augenstern.sys.domain.User;
import com.Augenstern.sys.mapper.RoleMapper;
import com.Augenstern.sys.mapper.UserMapper;
import com.Augenstern.sys.service.UserService;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 用户登录
     */
    @Override
    public User login(UserVo userVo) {
        //将明文密码转为密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    /**
     * 查询所有用户
     */
    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> data = this.userMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加用户
     */
    @Override
    public void addUser(UserVo userVo) {
        // 设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        // 设置用户类型（默认都是普通用户 type=2）
        userVo.setType(SysConstant.USER_TYPE_NORMAL);
        this.userMapper.insertSelective(userVo);
    }

    /**
     * 修改用户
     */
    @Override
    public void updateUser(UserVo userVo) {
        this.userMapper.updateByPrimaryKeySelective(userVo);
    }

    /**
     * 根据id删除用户
     */
    @Override
    public void deleteUser(Integer userid) {
        this.userMapper.deleteByPrimaryKey(userid);
        // 根据用户id删除 sys_role_user 里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);
    }

    /**
     * 批量删除用户
     */
    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer uid : ids) {
            deleteUser(uid);
        }
    }

    /**
     * 重置密码
     */
    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        // 设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        // 更新
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 加载用户管理的分配角色的数据
     */
    @Override
    public DataGridView queryUserRole(Integer userid) {
        // 查询所有可用的角色
        Role role = new Role();
        role.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(role);
        // 根据用户id查询已拥有的角色
        List<Role> userRole = this.roleMapper.queryRoleByUid(SysConstant.AVAILABLE_TRUE, userid);
        List<Map<String,Object>> data = new ArrayList<>();
        for (Role r1 : allRole) {
            Boolean LAY_CHECKED = false;
            for (Role r2 : userRole) {
                if(r1.getRoleid() == r2.getRoleid())
                    LAY_CHECKED = true;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    /**
     * 保存用户和角色的关系
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        // 根据用户id删除 sys_role_user 里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);
        // 保存关系
        if(roleIds != null && roleIds.length > 0) {
            for (Integer rid : roleIds) {
                this.userMapper.insertUserRole(userid, rid);
            }
        }
    }
}
