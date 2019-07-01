package com.cgl.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 16:08
 * @Description: 数据库链接对象获取工具类
 */
public class DBUtil {
    private static Connection con = null;
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void CloseConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
