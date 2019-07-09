package com.cgl.servlet;

import com.cgl.dao.ItemDaoImp;
import com.cgl.entity.Items;

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
 * @Date: 2019/7/6 19:53
 * @Description:用户投票实现
 */
@WebServlet(name = "AddItemsServlet",urlPatterns = "/addItem")
public class AddItemsServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String[] options = request.getParameterValues("option");
         int userId = Integer.valueOf(request.getParameter(CLIENT_USER_ID));
         int subjectId = Integer.valueOf(request.getParameter(CLIENT_SUBJECT_ID));
         String[] orders = request.getParameterValues("optionOrder");
         List<Items> itemsList = new ArrayList<Items>();
        for(int i=0; i<options.length; i++){
            Items items = new Items();
            items.setUserId(userId);
            items.setOptionId(Integer.valueOf(options[i]));
            items.setSubjectId(subjectId);
            items.setOptionOrder(Integer.valueOf(orders[i]));
            itemsList.add(items);

        }
        ItemDaoImp itemDaoImp = new ItemDaoImp();
        int rs = itemDaoImp.add(itemsList);
        if(rs>0){
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("error");
        }
     }
}
