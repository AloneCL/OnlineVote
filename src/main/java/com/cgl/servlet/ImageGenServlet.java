package com.cgl.servlet;

import com.cgl.tools.RandomValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/6/29 16:40
 * @Description:获取图片验证码
 */
@WebServlet(name = "validateCode",urlPatterns = "/getValidate")
public class ImageGenServlet extends HttpServlet implements FinalConstant{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Set-Cookie","name=value;HttpOnly");
        response.setDateHeader("Expire",0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();

        randomValidateCode.getRandcode(request,response,SESSION_VALIDATECODE);
    }
}
