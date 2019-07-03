package com.cgl.dao;

import com.cgl.entity.Option;
import com.cgl.entity.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 11:06
 * @Description:  投票标识对象Dao层接口操作接口定义
 */
public interface SubjectDao {
    int add(Subject v, List<Option> options) throws SQLException;
    int update(Subject v);
    Subject findById(Integer id);
    Subject findByName(String name);
    List<Subject> findAll();
    int deleteById(Integer id);
}
