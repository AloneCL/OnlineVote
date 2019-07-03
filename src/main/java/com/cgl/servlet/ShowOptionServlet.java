package com.cgl.servlet;

import com.cgl.dao.SubjectDaoImp;
import com.cgl.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 10:05
 * @Description: 展示所有投票
 */
@WebServlet(name = "ShowOptionServlet",urlPatterns = "/showOption")
public class ShowOptionServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDaoImp subjectDaoImp = new SubjectDaoImp();
        List<Subject> list = new ArrayList<Subject>();
        list = subjectDaoImp.findAll();
        request.setAttribute("subjectList",list);
        request.getRequestDispatcher("subjectList.jsp").forward(request,response);
    }
}
