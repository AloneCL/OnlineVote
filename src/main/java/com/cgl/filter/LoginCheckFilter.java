package com.cgl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/2 09:42
 * @Description:  检验用户登录拦截拦截器
 */
public class LoginCheckFilter implements Filter {
    String[] ignoreArr = null;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(isIgnore(request)){
            System.out.println("忽略的操作路径");
            chain.doFilter(request,response);
        }else {
            System.out.println("被过滤器拦截的操作");
            String user = (String) request.getSession().getAttribute("user");
            String admin = (String) request.getSession().getAttribute("admin");
            if(user!=null || admin!=null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("error","请登录之后在进行操作");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        }

    }

    public void init(FilterConfig config) throws ServletException {
       ignoreArr = config.getInitParameter("ignore").split(",");
    }

    public boolean isIgnore(HttpServletRequest request) {
        String path = request.getRequestURI();
        for (String ignore : ignoreArr) {
            if (path.contains(ignore)) {
                return true;
            }
        }
        System.out.println(path);
        return false;
    }

}
