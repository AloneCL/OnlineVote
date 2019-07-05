package com.cgl.dao;

import com.cgl.entity.Option;

import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 11:09
 * @Description: 投票选项标识Dao层操作接口
 */
public interface OptionDao {
    /**
     * 增加选项
     * @param v
     * @return
     */
    int add(Option v);

    /**
     * 更新选项信息
     * @param v
     * @return
     */
    int update(Option v);

    /**
     * 根据id来查找选项
     * @param id
     * @return
     */
    Option findById(Integer id);

    /**
     * 根据投票id来统一查询选项  查看投票的所有选项
     * @param sid
     * @return
     */
    List<Option> findBySid(Integer sid);

    /**
     * 删除选项
     * @param id
     * @return
     */
    int delete(int id);
}
