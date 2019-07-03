package com.cgl.dao;

import com.cgl.entity.Option;

import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 11:09
 * @Description: 投票选项标识Dao层操作接口
 */
public interface OptionDao {
    int add(Option v);
    int update(Option v);
    Option findById(Integer id);
    List<Option> findBySid(Integer sid);
    int delete(int id);
}
