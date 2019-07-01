package com.cgl.servlet;

import com.cgl.dao.UserDaoImp;
import com.cgl.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/1 09:34
 * @Description: 用户注册信息提交处理
 */
@WebServlet(name = "registerSubmit",urlPatterns = "/submitRegister")
public class SubmitRegister extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImp userDaoImp = new UserDaoImp();
        User user = new User();
        String name = request.getParameter("userName");
        String pass = request.getParameter("userPass");
        System.out.println(name + " " + pass);
        user.setUserName(name);
        user.setUserPass(pass);

        int rs = userDaoImp.add(user);
        if (rs == 3){
           request.setAttribute(REQUEST_ERROR_INFO,LOGIN_ERROR_FORMAT);
           request.getRequestDispatcher("userRegister").forward(request,response);
        }else if(rs == 0){
            request.setAttribute(REQUEST_ERROR_INFO,REGISTER_ERROR_ALIVE);
            request.getRequestDispatcher("userRegister").forward(request,response);
        }
        else{
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
