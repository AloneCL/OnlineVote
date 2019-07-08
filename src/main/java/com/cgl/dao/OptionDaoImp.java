package com.cgl.dao;

import com.cgl.entity.Option;
import com.cgl.tools.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/5 19:28
 * @Description:  投票选项标识Dao层方法实现
 */
public class OptionDaoImp implements OptionDao {


    public int add(Option v) {
        Connection con = DBUtil.getConnection();
        String sql = "insert into tb_option(option_name,subject_id,option_order) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getOption());
            ps.setInt(2, v.getSubjectId());
            ps.setInt(3, v.getOrder());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
        }

        return 0;
    }

    @Override
    public int update(Option v) {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "update tb_option option_name=? where option_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(2,v.getId());
            ps.setString(1,v.getOption());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return 0;
    }

    @Override
    public Option findById(Integer id) {
        return null;
    }


    @Override
    public List<Option> findBySid(Integer sid) {
        Connection con = DBUtil.getConnection();
        String sql = "select * from tb_option where subject_id = ? order by option_order";
        List<Option> list = new ArrayList<Option>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Option option = new Option();
                option.setOrder(rs.getInt("option_order"));
                option.setOption(rs.getString("option_name"));
                option.setSubjectId(sid);
                option.setId(rs.getInt("option_id"));
                list.add(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
        }
        return list;
    }

    @Override
    public int delete(int id) {
        Connection con = DBUtil.getConnection();
        String sql = "delete from tb_option where option_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return 0;
    }
}
