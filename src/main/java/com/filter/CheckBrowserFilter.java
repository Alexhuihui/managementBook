package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckBrowserFilter implements Filter {
    private boolean checked ;
    @Override
    public void init(FilterConfig config) throws ServletException {
        String c = config.getInitParameter("checked") ;
        this.checked = Boolean.valueOf( c ) ;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if( checked ) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            String uri = request.getRequestURI() ;
            String path = request.getContextPath() ;
            uri = uri.substring(path.length()) ;
            String userAgent = request.getHeader("user-agent");
            if (userAgent.toLowerCase().indexOf("edge") > -1) {
                if( uri.startsWith("/go")){
                    chain.doFilter( req , resp);
                    return;
                }
            } else if (userAgent.toLowerCase().indexOf("chrome") > -1) {
                // 让访问
                chain.doFilter(request, resp);
                return;
            } else if (userAgent.toLowerCase().indexOf("firefox") > -1) {
                // 让访问
                chain.doFilter(request, resp);
                return;
            } else if( userAgent.toLowerCase().indexOf("trident") > -1){
                if( uri.startsWith("/go")){
                    chain.doFilter( req , resp);
                    return;
                }
            }else{ // 其他浏览器
                 }
            response.sendRedirect( path + "/pages/download/downLoad.html");
        }else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
