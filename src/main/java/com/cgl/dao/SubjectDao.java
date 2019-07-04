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

    /**
     * 获取所有的信息数量 用于分页
     * @return
     */
    int getCount();

    Subject findById(Integer id);
    Subject findByName(String name);
    List<Subject> findAll();

    /**
     * 分页查询
     * @param start  开始序号  数据库从第一条数据从0开始
     * @param pageSize   每页所拥有的数据条数
     * @return
     */
    List<Subject> findByPage(int start,int pageSize);
    int deleteById(Integer id);
}
