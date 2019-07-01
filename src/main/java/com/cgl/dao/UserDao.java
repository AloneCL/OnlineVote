package com.cgl.dao;

import com.cgl.entity.User;

import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 16:23
 * @Description:  用户dao层方法声明 接口
 */
public interface UserDao {
    int login(User v);
    int add(User v);
    int update(User v);
    int delete(Integer id);
    List<User> findAll();
    User findById(Integer id);
    User findByName(String name);
}
