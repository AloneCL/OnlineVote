package com.cgl.dao;

import com.cgl.entity.Items;
import com.cgl.entity.OrderNum;
import com.cgl.tools.DBUtil;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/6 19:31
 * @Description:  ItemDao层接口方法实现
 */
public class ItemDaoImp implements ItemsDao {

    @Override
    public int add(List<Items> items) {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "insert into tb_item(option_id,subject_id,user_id,option_order) values(?,?,?,?)";

        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            for(Items v:items){
                System.out.println(v);
                ps.setInt(1,v.getOptionId());
                ps.setInt(2,v.getSubjectId());
                ps.setInt(3,v.getUserId());
                ps.setInt(4,v.getOptionOrder());
                ps.addBatch();
            }
            int rs[] =  ps.executeBatch();
            con.commit();

            for(int i=0; i<rs.length; i++){
                if(rs[i]==0){
                    System.out.println("批量提交出现异常，=====回滚====");
                    con.rollback();
                    return 0;
                }
            }
            return rs.length;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return 0;
    }


    @Override
    public List<Items> findByUserId(Integer userId,Integer subjectId) {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        List<Items> list = new ArrayList<Items>();
        String sql = "select * from tb_item where user_id = ? and subject_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Items items = new Items();
                items.setOptionId(rs.getInt("option_id"));
                items.setUserId(rs.getInt("user_id"));
                items.setSubjectId(rs.getInt("subject_id"));
                items.setId(rs.getInt("item_id"));
                list.add(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Items> findBySubject(Integer subjectId) {
        return null;
    }


    @Override
    public int optionNum(Integer subjectId){
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "select count(1) as num from tb_item where subject_id =?";
        int num  = 0;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                num = rs.getInt("num");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return num;
    }

    @Override
    public List<OrderNum> searchNum(Integer subjectId) {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        List<OrderNum> list = new ArrayList<OrderNum>();
        String sql = "select option_order,option_id,count(*) as num from tb_item where subject_id = ? group by option_order order by option_order";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderNum orderNum = new OrderNum();
                orderNum.setOptionOrder(rs.getInt("option_order"));
                orderNum.setOrderId(rs.getInt("option_id"));
                orderNum.setOrderNum(rs.getInt("num"));
                list.add(orderNum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return list;
    }
}
