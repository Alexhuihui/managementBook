package com.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.*;

public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        if(Objects.isNull(encoding) && encoding.trim().length() <= 0){
            this.encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
