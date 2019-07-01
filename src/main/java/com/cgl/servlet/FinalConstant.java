package com.cgl.servlet;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 16:47
 * @Description: 常量控制器 各类常量都在此定义
 */
public interface FinalConstant {
    /**
     * 服务器存放在Session中的验证码属性名
     */
    String SESSION_VALIDATECODE = "imagecode";

    /**
     * 存放于request中的客户端输入的图片验证码内容
     */
    String REQUEST_VALIDATECODE = "validateCode";

    /**
     * 存放于request对象中的错误提示信息 用于注册或登录验证
     */
    String REQUEST_ERROR_INFO = "error";

    /**
     * 登陆时密码错误的错误类型
     */
    String LOGIN_ERROR_PASS = "1";

    /**
     * 验证码错误的错误类型
     */
    String LOGIN_ERROR_VALIDATE = "2";

    /**
     * 后端账号密码格式验证
     */
    String LOGIN_ERROR_FORMAT = "3";

    /**
     * 后端验证用户账号已经存在
     */
    String REGISTER_ERROR_ALIVE = "0";
}
