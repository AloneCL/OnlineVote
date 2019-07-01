package com.cgl.entity;


import java.io.Serializable;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 15:43
 * @Description:  用户持久化类
 */
public class User implements Serializable {

    private static final long serialVersionUID = 9141128161030536187L;

    private int id;
    private String userName;
    private String userPass;
    private int userStatus;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
