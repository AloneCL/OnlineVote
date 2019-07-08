package com.cgl.servlet;

import com.cgl.dao.ItemDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/6 20:44
 * @Description:  检验用户是否已经参与过此投票
 */
@WebServlet(name = "CheckVoteServlet",urlPatterns = "/checkVote")
public class CheckVoteServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter(CLIENT_USER_ID));
        int subjectId = Integer.valueOf(request.getParameter("subjectId"));
        ItemDaoImp itemDaoImp = new ItemDaoImp();

        if (itemDaoImp.findByUserId(userId,subjectId)!=null){
            response.getWriter().print("false");
        }else
            response.getWriter().print("true");
    }

}
