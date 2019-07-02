package com.cgl.entity;

import java.io.Serializable;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 10:37
 * @Description: 选项类 一个选项可以被多个投票使用
 */
public class Option implements Serializable {
    private static final long serialVersionUID = 3704214687607795013L;

    private int id;    //选项标识id  对应数据库option_id
    private String option;    //选项内容名称  对应数据库表的option_name
    private int subjectId;      //外键标识  标识该选项被哪个投票使用了  对应subject_id
    private int order;         //排序标识 对应option_order

    public Option(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", option='" + option + '\'' +
                ", subjectId=" + subjectId +
                ", order=" + order +
                '}';
    }
}
