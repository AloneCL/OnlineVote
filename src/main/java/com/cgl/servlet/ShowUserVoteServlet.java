package com.cgl.servlet;

import com.cgl.dao.SubjectDaoImp;
import com.cgl.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/10 15:41
 * @Description:
 */
@WebServlet(name = "ShowUserVoteServlet",urlPatterns = "/userVotes")
public class ShowUserVoteServlet extends HttpServlet implements  FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDaoImp subjectDaoImp = new SubjectDaoImp();
        Cookie[] cookies = null;
        Cookie cookie = null;
        int userId = -1;            //筛选的用户id

        cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++){
            cookie = cookies[i];
            if(cookie.getName().compareTo(CLIENT_USER_ID)==0){
                userId = Integer.valueOf(cookie.getValue());
                break;
            }
        }

        int type = 0;
        if(request.getParameter("searchType")!=null){
            type = Integer.valueOf(request.getParameter("searchType"));   //查询的状态
            System.out.println("前台传递的值是："+type);
        }
        if(type == -1)
            type = 0;
        List<Subject> list = new ArrayList<Subject>();
        int count = subjectDaoImp.getCountForUser(userId,type);     //所有信息条数
        String page = request.getParameter("page");   //前台传过来的页面数值
        boolean hasNext = true;
        int totalPage = count%PAGE_SIZE >0 ? count/PAGE_SIZE+1 : count/PAGE_SIZE;
        int currentPage = 1;                   //当前页号
        if(page!=null){
            currentPage =   Integer.valueOf(page);
        }
        if(currentPage < 1)
            currentPage = 1;
        if(currentPage > totalPage) {
            currentPage = totalPage;
            hasNext = false;
        }

        if(totalPage == 0)
            currentPage = 1;



        list = subjectDaoImp.findByUserId(userId,(currentPage-1)*PAGE_SIZE,PAGE_SIZE,type);
        request.setAttribute("type",type);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("total",count);
        request.setAttribute("hasNext",hasNext);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("subjectList",list);
        request.getRequestDispatcher("userSubjectList.jsp").forward(request,response);
    }
}
