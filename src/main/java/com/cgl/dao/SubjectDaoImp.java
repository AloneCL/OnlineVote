package com.cgl.dao;

import com.cgl.entity.Option;
import com.cgl.entity.Subject;
import com.cgl.tools.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 14:54
 * @Description: 投票标识Dao层接口方法实现
 */
public class SubjectDaoImp implements SubjectDao {
    /**
     * 添加投票信息 用于新建投票 同时添加选项便于事务管理 保证两张表同时添加信息的原子性
     * @param v  输入投票基本信息
     * @param options  投票的包含的基本选项集合
     * @return
     */
    @Override
    public int add(Subject v, List<Option> options) throws SQLException{
        Connection con = DBUtil.getConnection();
        String sql = "insert into tb_subject(subject_title,subject_type) values(?,?)";
        String sql2 = "insert into tb_option(option_name,subject_id,option_order) values(?,?,?)";
        boolean flag = con.getAutoCommit();
        con.setAutoCommit(false);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,v.getTitle());
            ps.setInt(2,v.getType());
            int rs = ps.executeUpdate();
            int subjectId = -1;
            if(rs>0){
                String sql1 = "select last_insert_id() as id ";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                con.commit();
                while(rs1.next()){
                    subjectId =  rs1.getInt("id");
                }
            }else {
                con.rollback();
            }
            PreparedStatement ps2 = con.prepareStatement(sql2);
            for(Option o:options){
                System.out.println(o);
                ps2.setString(1,o.getOption());
                ps2.setInt(2,subjectId);
                ps2.setInt(3,o.getOrder());
                ps2.addBatch();
            }
            int[] option = ps2.executeBatch(); //批量提交处理
            con.commit();
            for(int i : option){
                if(i==0){
                    System.out.println("批量提交出现异常，=====回滚====");
                    con.rollback();
                }
            }
        } catch (SQLException e) {
            con.rollback();
            System.out.println("添加投票失败");
            throw new SQLException();
        }finally {
            DBUtil.CloseConnection(con);
        }
        return 0;
    }

    /**
     * 更新数据
     * @param v
     * @return 更新成功返回1
     */
    @Override
    public int update(Subject v) {
        Connection con = DBUtil.getConnection();
        String sql = "update tb_subject subject_title = ?,subject_type=? where subject_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,v.getTitle());
            ps.setInt(2,v.getType());
            ps.setInt(3,v.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        Connection con = DBUtil.getConnection();
        String sql = "select count(1) as count from tb_subject";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }
        return 0;
    }

    /**
     * 根据id索引查找数据
     * @param id
     * @return
     */
    @Override
    public Subject findById(Integer id) {
        Connection con = DBUtil.getConnection();
        String sql = "select * from tb_subject where subject_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subject subject = new Subject();
                subject.setId(id);
                subject.setTitle(rs.getString("subject_title"));
                subject.setType(rs.getInt("subject_type"));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }
        return null;
    }

    /**
     * 根据投票名称来查找数据
     * @param name
     * @return
     */
    @Override
    public Subject findByName(String name) {
        Connection con = DBUtil.getConnection();
        String sql = "select * from tb_subject where subject_title = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setTitle(rs.getString("subject_title"));
                subject.setType(rs.getInt("subject_type"));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }
        return null;
    }

    /**
     * 直接获取所有的投票信息
     * @return
     */
    @Override
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<Subject>();
        Connection con = DBUtil.getConnection();
        String sql = "select * from tb_subject";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setTitle(rs.getString("subject_title"));
                subject.setType(rs.getInt("subject_type"));
                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }

        return list;
    }

    /**
     *
     * @param start  开始序号  数据库从第一条数据从0开始
     * @param pageSize   每页所拥有的数据条数
     * @return
     */
    @Override
    public List<Subject> findByPage(int start, int pageSize) {
        PreparedStatement ps = null;
        Connection con = DBUtil.getConnection();
        String sql = "select * from tb_subject limit ?,?";
        List<Subject> subjectList = new ArrayList<Subject>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,start);
            ps.setInt(2,pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setTitle(rs.getString("subject_title"));
                subject.setType(rs.getInt("subject_type"));
                subjectList.add(subject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return  返回删除的数量
     */
    @Override
    public int deleteById(Integer id) {
        Connection con = DBUtil.getConnection();
        String sql = "delete from tb_subject where subject_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.CloseConnection(con);
        }

        return 0;
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.setTitle("添加投票测试2");
        subject.setType(1);
        SubjectDaoImp subjectDaoImp = new SubjectDaoImp();
        System.out.println(subjectDaoImp.getCount());
       /* List<Subject> list = subjectDaoImp.findAll();
        for (Subject s: list
        ) {
            System.out.println(s);
        }*/
    }
}
