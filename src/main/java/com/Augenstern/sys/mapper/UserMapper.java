package com.Augenstern.sys.mapper;

import com.Augenstern.sys.domain.User;
import com.Augenstern.sys.domain.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登录
     */
    User login(User user);

    /**
     * 查询用户
     */
    List<User> queryAllUser(User user);

    /**
     * 保存用户和角色的关系
     */
    void insertUserRole(@Param("uid")Integer userid, @Param("rid")Integer rid);
}