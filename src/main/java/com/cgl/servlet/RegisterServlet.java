package com.cgl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 19:58
 * @Description:
 */
@WebServlet(name = "register",urlPatterns = "/userRegister")
public class RegisterServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = (String) request.getAttribute(REQUEST_ERROR_INFO);
        if(error != null) {
            if (error.equals(REGISTER_ERROR_ALIVE)) {
           request.setAttribute(REQUEST_ERROR_INFO,"该账号已经被注册");
            } else if (error.equals(LOGIN_ERROR_FORMAT)) {
                request.setAttribute(REQUEST_ERROR_INFO,"账号密码格式输入错误");
            }
        }
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }


}
