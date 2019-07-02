package com.cgl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/1 15:21
 * @Description:  用户登录处理servlet
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/userLogin")
public class LoginServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String error = (String) request.getAttribute(REQUEST_ERROR_INFO);
        if(error!=null){
            if(error.equals(LOGIN_ERROR_FORMAT)){
                request.setAttribute(REQUEST_ERROR_INFO,"请输入的正确的用户名密码格式");
                request.getRequestDispatcher("login.jsp");
            }else if (error.equals(LOGIN_ERROR_VALIDATE)){
                request.setAttribute(REQUEST_ERROR_INFO,"验证码错误");
                request.getRequestDispatcher("login.jsp");
            }
            else if(error.equals(LOGIN_ERROR_PASS)){
                request.setAttribute(REQUEST_ERROR_INFO,"密码错误");
                request.getRequestDispatcher("login.jsp");
            }else if (error.equals(REGISTER_ERROR_ALIVE)){
                request.setAttribute(REQUEST_ERROR_INFO,"用户名不存在");
                request.getRequestDispatcher("login.jsp");
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
