package com.cgl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 10:28
 * @Description:  投票主题内容标识  一个主题包含多个投票选项
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = -8799767630390421752L;
    private int id;     //唯一标识 对应主键 subject_id
    private  String title;    //投票内容标识  对应subject_title
    private  int type;         //投票类别  多选或单选 1为单选 2为多选
    private int userId;       //发起投票的用户id
    private Date startTime;   //投票发起时间
    private Date endTime;          //投票截止时间
    private String status;            //投票的状态 是否已过期或剩余多少时间过期

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                '}';
    }
}
