package com.cgl.servlet;

import com.cgl.dao.SubjectDaoImp;
import com.cgl.entity.Option;
import com.cgl.entity.Subject;
import com.cgl.tools.DateConventer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/3 19:14
 * @Description: 添加投票选项接收前台数据并添加投票标识和投票选项标识
 */
@WebServlet(name = "AddSubjectServlet",urlPatterns = "/addSubject")
public class AddSubjectServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = null;
        Cookie cookie = null;
        try {
            String title = request.getParameter("subjectTitle");
            int type = 0;
            if(!request.getParameter("types").equals(""))
               type = Integer.valueOf(request.getParameter("types"));
               else{
                   type = 1;
            }
            String[] options = request.getParameterValues("option");
            String endTime = request.getParameter("endTime");
            Date end = null;
            if(!endTime.equals("")){
                end = DateConventer.dateConvent(endTime);
            }
            if (end==null||new Date().compareTo(end) < 0) { //前台没有设置时间或者设置了错误的时间
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);
                end = calendar.getTime();
            }

            System.out.println(endTime);
            int userId = -1;
            cookies = request.getCookies();
            for(int i = 0;i<cookies.length; i++){
                cookie = cookies[i];
                if(cookie.getName().compareTo(CLIENT_USER_ID)==0){
                    userId = Integer.valueOf(cookie.getValue());
                    break;
                }
            }
            Subject subject = new Subject();
            subject.setType(type);
            subject.setTitle(title);
            subject.setUserId(userId);
            subject.setStartTime(new Timestamp(new Date().getTime()));
            subject.setEndTime(new Timestamp(end.getTime()));

            List<Option> optionsList = new ArrayList<Option>();
            for(int i = 0; i<options.length; i++){
                Option option1 = new Option();
                option1.setOption(options[i]);
                option1.setOrder(i+1);
                optionsList.add(option1);
                System.out.println(option1);
            }
            System.out.println(subject);
            SubjectDaoImp subjectDaoImp = new SubjectDaoImp();
            subjectDaoImp.add(subject,optionsList);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("showSubject");
    }
}
