package com.cgl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: CGL
 * @Date: 2019/7/3 19:34
 * @Description:  用于统一设置request和response的编码
 */
public class EncodingFilter implements Filter {
    private FilterConfig config;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse hrsp  = (HttpServletResponse) resp;
        HttpServletRequest hreq  = (HttpServletRequest) req;
        hreq.setCharacterEncoding(config.getInitParameter("encoding"));
        hrsp.setContentType(config.getInitParameter("contentType"));
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
