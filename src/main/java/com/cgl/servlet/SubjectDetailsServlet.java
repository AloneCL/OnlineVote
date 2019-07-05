package com.cgl.servlet;

import com.cgl.dao.OptionDaoImp;
import com.cgl.entity.Option;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        OptionDaoImp daoImp = new OptionDaoImp();
        List<Option> optionList = daoImp.findBySid(subjectId);
        for (Option o: optionList){
            System.out.println(o);
        }
        request.setAttribute("optionList",optionList);
    }
}
