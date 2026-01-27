package com.springboot.springboot2.mapper;

import com.github.pagehelper.Page;
import com.springboot.springboot2.pojo.UnpaidOwner;
import com.springboot.springboot2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 Mapper 接口
 * 对应 XML：resources/mapper/UserMapper.xml
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 新增用户
     */
    int insertUser(User user);

    /**
     * 根据 ID 删除用户
     */
    int deleteUserById(@Param("id") Integer id);

    /**
     * 更新用户信息
     */
    int updateUser(User user);

    /**
     * 查询所有用户
     */
    List<User> selectAllUsers();

    /**
     * 根据账号查询用户
     */
    User selectUserByAccount(@Param("account") String account);

    /**
     * 检查账号是否存在
     */
    int countByAccount(@Param("account") String account);

    /**
     * 分页查询业主信息
     */
    Page<User> owners();

    /**
     * 根据 ID 查询用户信息
     */

    User queryById(Integer id);

    /**
     * 修改用户状态
     */
    int changeUserStatus(User user);

    /**
     * 分页查询未缴费业主列表
     */
    Page<UnpaidOwner> unpaidOwnerList(@Param("typeId") Integer typeId);
}
