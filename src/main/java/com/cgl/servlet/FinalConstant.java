package com.cgl.servlet;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 16:47
 * @Description: 常量控制器 各类常量都在此定义
 */
public interface FinalConstant {

    /**
     * 存放于请求中传到后端的投票标识id
     */
    String CLIENT_SUBJECT_ID = "subjectId";

    /**
     * 分页查询每页信息的条数
     */
    int PAGE_SIZE = 10;
    /**
     * 服务器存放在Session中的验证码属性名
     */
    String SESSION_VALIDATECODE = "imagecode";

    /**
     * 存放于客户端 cookie中的用户id 用于添加外键信息
     */
    String CLIENT_USER_ID = "userId";

    /**
     * 登录后存放于服务器session中的用户名 用于判断登录等
     */
    String SESSION_USER = "user";
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
    String LOGIN_ERROR_PASS = "-1";

    /**
     * 验证码错误的错误类型
     */
    String LOGIN_ERROR_VALIDATE = "2";

    /**
     * 后端账号密码格式验证
     */
    String LOGIN_ERROR_FORMAT = "3";

    /**
     * 后端验证用户账号已经存在或登录不存在
     */
    String REGISTER_ERROR_ALIVE = "0";
}
