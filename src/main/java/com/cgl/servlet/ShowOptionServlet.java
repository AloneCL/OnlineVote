package com.cgl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 10:05
 * @Description: 展示所有投票
 */
@WebServlet(name = "ShowOptionServlet",urlPatterns = "/showOption")
public class ShowOptionServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(111111);
    }
}
