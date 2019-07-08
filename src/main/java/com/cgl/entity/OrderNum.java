package com.cgl.entity;

import java.io.Serializable;

/**
 * @Auther: CGL
 * @Date: 2019/7/8 09:33
 * @Description: 用于存储投票选项数量的工具类
 */
public class OrderNum implements Serializable {
    private static final long serialVersionUID = -658999135087025376L;
    private int optionOrder;          //选项序号
    private int orderNum;           // 选项的票数
    private int orderId;             //选项id
    private int percent;             //占的百分比
    private boolean userIsVote;         //用户是否投了此选项


    public boolean isUserIsVote() {
        return userIsVote;
    }

    public void setUserIsVote(boolean userIsVote) {
        this.userIsVote = userIsVote;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(int optionOrder) {
        this.optionOrder = optionOrder;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderNum{" +
                "optionOrder=" + optionOrder +
                ", orderNum=" + orderNum +
                ", orderId=" + orderId +
                ", percent=" + percent +
                ", userIsVote=" + userIsVote +
                '}';
    }
}
