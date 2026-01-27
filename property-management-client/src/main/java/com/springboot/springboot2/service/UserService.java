package com.springboot.springboot2.service;

import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.UnpaidOwner;
import com.springboot.springboot2.pojo.User;
import org.springframework.dao.DuplicateKeyException;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 插入成功的记录数（一般是 1）
     * @throws DuplicateKeyException 唯一索引冲突
     * @throws Exception             其他异常
     */
    int insertUser(User user) throws DuplicateKeyException, Exception;

    /**
     * 分页查询业主信息
     *
     * @param current 当前页码
     * @param size    每页条数
     * @return PageResult<User>
     */
    PageResult<User> pageOfOwner(int current, int size);

    /**
     * 修改用户状态
     *
     * @param user 用户对象（含 userId 和 userStatus）
     * @return 更新结果（0 失败，1 成功）
     */
    int changeUserStatus(User user);

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户对象
     */
    User queryById(Integer id);
    /**
     * 添加pageOfUnpaidOwnerList方法，用于查询未缴费业主列表
     */
    PageResult<UnpaidOwner> pageOfUnpaidOwnerList
    (Integer current, Integer size, Integer typeId);
}
