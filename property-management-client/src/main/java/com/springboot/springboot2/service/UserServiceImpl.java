package com.springboot.springboot2.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.springboot2.mapper.UserMapper;
import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.UnpaidOwner;
import com.springboot.springboot2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户
     */
    @Override
    public int insertUser(User user) throws DuplicateKeyException, Exception {
        return userMapper.insertUser(user);
    }

    /**
     * 分页查询业主信息
     */
    @Override
    public PageResult<User> pageOfOwner(int current, int size) {
        if (current < 1) current = 1;
        if (size < 1) size = 10;

        // 开启分页
        PageHelper.startPage(current, size);

        // 查询结果
        Page<User> page = userMapper.owners();

        // 封装分页结果
        return PageResult.restPage(page);
    }

    /**
     * 修改用户状态
     */
    @Override
    public int changeUserStatus(User user) {
        return userMapper.changeUserStatus(user);
    }

    /**
     * 通过ID查询用户
     */
    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

    /**
     * 分页查询未缴费业主列表
     */
    @Override
    public PageResult<UnpaidOwner> pageOfUnpaidOwnerList(Integer current, Integer size, Integer typeId) {
       PageHelper.startPage(current, size);
       Page<UnpaidOwner> page = userMapper.unpaidOwnerList(typeId);
       return PageResult.restPage(page);
    }

}
