package com.cgl.dao;

import com.cgl.entity.Option;
import com.cgl.entity.Subject;
import com.cgl.tools.DBUtil;
import com.cgl.tools.DateConventer;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        String sql = "insert into tb_subject(subject_title,subject_type,user_id,startTime,endTime) values(?,?,?,?,?)";
        String sql2 = "insert into tb_option(option_name,subject_id,option_order) values(?,?,?)";
        boolean flag = con.getAutoCommit();
        con.setAutoCommit(false);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,v.getTitle());
            ps.setInt(2,v.getType());
            ps.setInt(3,v.getUserId());
            ps.setTimestamp(4, (Timestamp) v.getStartTime());
            ps.setTimestamp(5, (Timestamp) v.getEndTime());
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
            DBUtil.closeConnection(con);
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
            DBUtil.closeConnection(con);
        }

        return 0;
    }


    /**
     *
     * @param type 查询类型  根据状态查询 0为所有 1为进行中 2为已结束
     * @return
     */
    @Override
    public int getCount(int type) {
        Connection con = DBUtil.getConnection();
        String sql = "";
        if(type==0)
            sql = "select count(1) as count from tb_subject";
        else if (type==1)
            sql = "select count(1) as count from tb_subject where (select current_timestamp )< endTime";
        else
            sql = "select count(1) as count from tb_subject where (select current_timestamp) >endTime";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }
        return 0;
    }

    /**
     *
     * @param id  用户id
     * @param type  投票类型
     * @return
     */
    @Override
    public int getCountForUser(Integer id,int type) {
        Connection con = DBUtil.getConnection();
        String sql = "";
        if(type==0)
            sql = "select count(1) as count from tb_subject where user_id = ?";
        else if (type==1)
            sql = "select count(1) as count from tb_subject where user_id = ? and (select current_timestamp )< endTime";
        else
            sql = "select count(1) as count from tb_subject where user_id = ? and (select current_timestamp) >endTime";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
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
                subject.setUserId(rs.getInt("user_id"));
                subject.setStartTime(rs.getTimestamp("startTime"));
                subject.setEndTime(rs.getTimestamp("endTime"));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
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
                subject.setUserId(rs.getInt("user_id"));
                subject.setStartTime(rs.getTimestamp("startTime"));
                subject.setEndTime(rs.getTimestamp("endTime"));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
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
                subject.setUserId(rs.getInt("user_id"));
                subject.setStartTime(rs.getTimestamp("startTime"));
                subject.setEndTime(rs.getTimestamp("endTime"));
                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(con);
        }

        return list;
    }

    /**
     *
     * @param id           用户id
     * @param start        开始序号
     * @param pageSize          每页查询的条数
     * @param type          查询的类型（0代表所有类型 1代表进行中 2代表已过期）
     * @return
     */
    public List<Subject> findByUserId(Integer id, int start, int pageSize, int type) {
        PreparedStatement ps = null;
        String sql = "";
        Connection con = DBUtil.getConnection();
        if(type==0)
            sql = "select * from tb_subject where user_id = ? limit ?,?";
        else  if(type == 1)
            sql = "select * from tb_subject where user_id = ? and (select current_timestamp) < endTime limit ?,?";
        else
            sql = "select * from tb_subject where user_id = ? and (select current_timestamp) > endTime limit ?,?";
        List<Subject> subjectList = new ArrayList<Subject>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,start);
            ps.setInt(3,pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setTitle(rs.getString("subject_title"));
                subject.setType(rs.getInt("subject_type"));
                subject.setUserId(rs.getInt("user_id"));
                subject.setStartTime(rs.getTimestamp("startTime"));
                subject.setEndTime(rs.getTimestamp("endTime"));
                subject.setStatus(DateConventer.TimeDifference(new Date().getTime(),subject.getEndTime().getTime()));
                subjectList.add(subject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    /**
     *
     * @param start  开始序号  数据库从第一条数据从0开始
     * @param pageSize   每页所拥有的数据条数
     * @param type      查询的类型 是否已过期  0代表所有类型 1代表进行中 2代表已过期
     * @return
     */
    @Override
    public List<Subject> findByPage(int start, int pageSize,int type) {
        PreparedStatement ps = null;
        String sql = "";
        Connection con = DBUtil.getConnection();
        if(type==0)
            sql = "select * from tb_subject limit ?,?";
        else  if(type == 1)
            sql = "select * from tb_subject where (select current_timestamp) < endTime limit ?,?";
        else
            sql = "select * from tb_subject where (select current_timestamp) > endTime limit ?,?";
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
                subject.setUserId(rs.getInt("user_id"));
                subject.setStartTime(rs.getTimestamp("startTime"));
                subject.setEndTime(rs.getTimestamp("endTime"));
                subject.setStatus(DateConventer.TimeDifference(new Date().getTime(),subject.getEndTime().getTime()));
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
            DBUtil.closeConnection(con);
        }

        return 0;
    }

    public static void main(String[] args) throws SQLException {
        Subject subject = new Subject();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SubjectDaoImp subjectDaoImp = new SubjectDaoImp();

       /* subject.setTitle("timestamp添加测试");
        subject.setType(1);
        subject.setEndTime(new Timestamp(new Date().getTime()));
        subject.setEndTime(new Timestamp(new Date().getTime()));
        subject.setUserId(1);
       List<Option> options = new ArrayList<Option>();
       options.add(new Option("11",10,2));
       options.add(new Option("22",10,1));
        subjectDaoImp.add(subject,options);*/
        List<Subject> list = subjectDaoImp.findByPage(0,5,0);
        // System.out.println(subjectDaoImp.getCount());
        for (Subject s: list
        ) {
            //  System.out.println(dd.format(s.getEndTime())+"  "+dd.format(s.getStartTime()));
            System.out.println(s);
        }
    }
}
