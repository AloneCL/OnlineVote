package com.cgl.servlet;

import com.cgl.dao.ItemDaoImp;
import com.cgl.entity.Items;
import com.cgl.entity.OrderNum;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Auther: CGL
 * @Date: 2019/7/8 10:00
 * @Description: 检索投票选项数量
 */
@WebServlet(name = "SearchNumServlet",urlPatterns = "/searchNum")
public class SearchNumServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter(CLIENT_SUBJECT_ID));
        int userId = Integer.valueOf(request.getParameter(CLIENT_USER_ID));

        ItemDaoImp itemDaoImp = new ItemDaoImp();
        List<OrderNum> orderNums = itemDaoImp.searchNum(id);
        List<Items> itemsList = itemDaoImp.findByUserId(userId,id);



        int optionNum = itemDaoImp.optionNum(id);
        for(OrderNum o:orderNums){
            o.setPercent(o.getOrderNum()*100/optionNum);
            o.setUserIsVote(false);
            for(Items i : itemsList){
                if(o.getOrderId() == i.getOptionId()){
                    o.setUserIsVote(true);
                }
            }
        }



        JSONArray jsonArray= JSONArray.fromObject(orderNums);
        PrintWriter writer = response.getWriter();

        writer.print(jsonArray.toString());
    }


   /* public static void main(String[] args) {
        int a = 6;
        int b = 11;
        int c = a*100/b;

        System.out.println(c);

    }*/
}
