package com.cgl.entity;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
