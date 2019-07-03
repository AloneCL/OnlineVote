package com.cgl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Auther: CGL
 * @Date: 2019/7/3 08:32
 * @Description: 验证图片验证码是否输入正确 用于ajax异步验证
 */
@WebServlet(name = "CheckCodeServlet",urlPatterns = "/checkCode")
public class CheckCodeServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String clientCode = request.getParameter(REQUEST_VALIDATECODE);
        HttpSession session = request.getSession();
        String serverCode = (String) session.getAttribute(session.getId()+SESSION_VALIDATECODE);

        if(serverCode.equalsIgnoreCase(clientCode)){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }
    }
}
