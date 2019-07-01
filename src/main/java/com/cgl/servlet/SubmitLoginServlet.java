package com.cgl.servlet;

import com.cgl.dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/1 15:41
 * @Description:
 */
@WebServlet(name = "SubmitLoginServlet",urlPatterns = "/submitLogin")
public class SubmitLoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImp userDaoImp = new UserDaoImp();


    }
}
