package com.Augenstern.sys.service;

import com.Augenstern.sys.domain.User;
import com.Augenstern.sys.utils.DataGridView;
import com.Augenstern.sys.vo.UserVo;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 查询所有用户
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     */
    void addUser(UserVo userVo);

    /**
     * 修改用户
     */
    void updateUser(UserVo userVo);

    /**
     * 根据id删除用户
     */
    void deleteUser(Integer userid);

    /**
     * 批量删除用户
     */
    void deleteBatchUser(Integer [] ids);

    /**
     * 重置密码
     */
    void resetUserPwd(Integer userid);

    /**
     * 加载用户管理的分配角色的数据
     */
    DataGridView queryUserRole(Integer userid);

    /**
     * 保存用户和角色的关系
     */
    void saveUserRole(UserVo userVo);
}
