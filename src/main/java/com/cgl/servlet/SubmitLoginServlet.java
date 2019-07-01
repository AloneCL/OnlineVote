package com.cgl.servlet;

import com.cgl.dao.UserDaoImp;
import com.cgl.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/1 15:41
 * @Description: 处理用户登录数据接收验证
 */
@WebServlet(name = "SubmitLoginServlet",urlPatterns = "/submitLogin")
public class SubmitLoginServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String clientCheckCode = request.getParameter(REQUEST_VALIDATECODE);
        String serverCheckCode = (String) request.getSession().getAttribute(session.getId()+SESSION_VALIDATECODE);
        if(!clientCheckCode.equalsIgnoreCase(serverCheckCode)){
            request.setAttribute(REQUEST_ERROR_INFO,LOGIN_ERROR_VALIDATE);
            request.getRequestDispatcher("userLogin").forward(request,response);
        }
        UserDaoImp userDaoImp = new UserDaoImp();
        User user = new User();
        user.setUserName(request.getParameter("userAccount"));
        user.setUserPass(request.getParameter("userPass"));
        int rs = userDaoImp.login(user);
        if (rs == 3){
            request.setAttribute(REQUEST_ERROR_INFO,LOGIN_ERROR_FORMAT);
            request.getRequestDispatcher("userLogin").forward(request,response);
        }else if(rs == 0){
            request.setAttribute(REQUEST_ERROR_INFO,REGISTER_ERROR_ALIVE);
            request.getRequestDispatcher("userLogin").forward(request,response);
        }else{

        }

    }
}
