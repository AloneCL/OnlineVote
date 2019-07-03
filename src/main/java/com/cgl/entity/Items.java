package com.cgl.entity;

import java.io.Serializable;

/**
 * @Auther: CGL
 * @Date: 2019/7/3 20:08
 * @Description:  投票取值标识 用于记录用户投票记录  某个投票的某个选项
 */
public class Items implements Serializable {
    private static final long serialVersionUID = -9151033093038922451L;
    private int id;
    private int optionId;
    private int subjectId;
    private int userId;

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
}
