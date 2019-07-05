package com.cgl.dao;

import com.cgl.entity.User;
import com.cgl.tools.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: CGL
 * @Date: 2019/7/1 09:14
 * @Description:  用户dao层接口方法实现类
 */
public class UserDaoImp implements UserDao {

    /**
     * 用户登录逻辑
     * 返回1为正确   返回3为格式错误 返回0则表示用户名不存在
     * @param v
     * @return
     */
    @Override
    public int login(User v) {
        if(!checkFormat(v.getUserName())|| v.getUserPass().isEmpty() || v.getUserPass().length()>16){
            return 3;
        }
        User u = findByName(v.getUserName());
        if(u!=null){
            if(v.getUserPass().equals(u.getUserPass())) return  1;
        }
        return 0;
    }

    /**
     * 添加用户 对应用户注册
     * @param v
     * @return 1表示插入成功 3表示格式错误 0表示改用户名已被注册
     */
    @Override
    public int add(User v) {
        if(!checkFormat(v.getUserName())|| v.getUserPass().isEmpty() || v.getUserPass().length()>16){
            return 3;
        }
        if(findByName(v.getUserName())!=null)
            return 0;
        Connection con = DBUtil.getConnection();
        String sql = "insert into tb_user (user_name,user_pass) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,v.getUserName());
            ps.setString(2,v.getUserPass());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return 0;
    }

    @Override
    public int update(User v) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    /**
     * 根据id来查找具体的对象信息
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        Connection con = DBUtil.getConnection();
        String sql  = "select * from tb_user where user_id = ?";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPass(rs.getString("user_pass"));
                user.setUserStatus(rs.getInt("user_status"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return null;
    }

    /**
     * 根据用户名来查找对象信息 用户名也是唯一标识
     * @param name
     * @return
     */
    @Override
    public User findByName(String name) {

        Connection con = DBUtil.getConnection();
        String sql  = "select * from tb_user where user_name = ?";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPass(rs.getString("user_pass"));
                user.setUserStatus(rs.getInt("user_status"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return null;
    }

    /**
     * 用户名格式检查
     * @param str
     * @return
     */
    public static boolean checkFormat(String str) {
        if(str==null)
            return false;
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("[0-9A-Za-z_]*"); // 验证组成格式
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
