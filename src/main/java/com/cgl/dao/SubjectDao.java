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
     * @param type 查询类型  根据状态查询 0为所有 1为进行中 2为已结束
     * @return
     */
    int getCount(int type);

    /**
     * 根据用户id查询用户创建的投票信息条数
     * @param id  用户id
     * @param type  投票类型
     * @return    用户创建的投票信息集合
     */
    int getCountForUser(Integer id,int type);

    Subject findById(Integer id);
    Subject findByName(String name);
    List<Subject> findAll();


    /**
     * 根据用id分页查询投票   用来实现用户查询自己发起的投票功能
     * @param id           用户id
     * @param start        开始序号
     * @param pageSize          每页查询的条数
     * @param type          查询的类型（0代表所有类型 1代表进行中 2代表已过期）
     * @return
     */
    List<Subject> findByUserId(Integer id,int start,int pageSize,int type);

    /**
     * 分页查询
     * @param start  开始序号  数据库从第一条数据从0开始
     * @param pageSize   每页所拥有的数据条数
     * @param type      查询的类型 是否已过期  0代表所有类型 1代表进行中 2代表已过期
     * @return
     */
    List<Subject> findByPage(int start,int pageSize,int type);
    int deleteById(Integer id);
}
