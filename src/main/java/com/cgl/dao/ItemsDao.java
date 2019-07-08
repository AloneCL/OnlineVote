package com.cgl.dao;

import com.cgl.entity.Items;
import com.cgl.entity.OrderNum;

import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/6 19:26
 * @Description: items数据库Dao层接口 维持用户投票与投票选项关系的表 记录用户的投票
 */
public interface ItemsDao {
    /**
     * 添加关系
     * @param v
     * @return
     */
    int add(List<Items> v);

    /**
     * 根据用户id查找  用于判断用户是否已参与投票
     * @param userId,subjectId
     * @return
     */
    List<Items> findByUserId(Integer userId,Integer subjectId);
    List<Items> findBySubject(Integer subjectId);

    /**
     * 统计投票各个选项的数量
     * @param subjectId
     * @return
     */
    List<OrderNum> searchNum(Integer subjectId);

    /**
     * 查找投票拥有的所有选项
     * @param subjectId
     * @return
     */
    int optionNum(Integer subjectId);

}
