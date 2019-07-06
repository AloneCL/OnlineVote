package com.cgl.servlet;

import com.cgl.dao.OptionDaoImp;
import com.cgl.dao.SubjectDaoImp;
import com.cgl.entity.Option;
import com.cgl.entity.Subject;
import com.cgl.tools.DateConventer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/5 19:50
 * @Description:
 */
@WebServlet(name = "SubjectDetailsServlet",urlPatterns = "/subjectDetail")
public class SubjectDetailsServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subjectId = Integer.valueOf(request.getParameter("sId"));
        SubjectDaoImp subjectDaoImp = new SubjectDaoImp();
        OptionDaoImp daoImp = new OptionDaoImp();
        Subject subject = subjectDaoImp.findById(subjectId);
        List<Option> optionList = daoImp.findBySid(subjectId);

        request.setAttribute("sTime", DateConventer.TimeDifference(new Date().getTime(),subject.getEndTime().getTime()));
        request.setAttribute("subject",subject);
        request.setAttribute("optionList",optionList);
        request.getRequestDispatcher("subjectDetail.jsp").forward(request,response);
    }
}
