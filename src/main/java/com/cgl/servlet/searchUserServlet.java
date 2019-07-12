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
 * @Date: 2019/7/12 08:57
 * @Description:  根据用户名检索
 */
@WebServlet(name = "searchUserServlet",urlPatterns = "/searchUser")
public class searchUserServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        UserDaoImp userDaoImp = new UserDaoImp();
        if(userDaoImp.findByName(username)!=null){
            response.getWriter().print("false");
        }else {
            response.getWriter().print("true");
        }
    }

}
