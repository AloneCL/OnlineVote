package com.cgl.entity;

import java.io.Serializable;

/**
 * @Auther: CGL
 * @Date: 2019/7/3 20:08
 * @Description:  投票取值标识 用于记录用户投票记录  某个投票的某个选项
 */
public class Items implements Serializable {
    private static final long serialVersionUID = -9151033093038922451L;
    private int id;              //主键id
    private int optionId;           //选项标识id
    private int subjectId;        //投票标识id
    private int userId;            //投票用户id

    public int getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(int optionOrder) {
        this.optionOrder = optionOrder;
    }

    private int optionOrder;      //选项标识的序号

    public Items(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", optionId=" + optionId +
                ", subjectId=" + subjectId +
                ", userId=" + userId +
                ", optionOrder=" + optionOrder +
                '}';
    }
}
