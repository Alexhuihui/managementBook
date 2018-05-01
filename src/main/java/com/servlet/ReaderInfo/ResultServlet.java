package com.servlet.ReaderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        HttpSession se=request.getSession();
        Enumeration<String>names=se.getAttributeNames();
        PrintWriter w= response.getWriter();
        while (names.hasMoreElements()){
            String name=names.nextElement();
            if(name.equals("session.mutex")||name.equals("org.apache.velocity.tools.Toolbox")){}else{
            Object o=se.getAttribute(name);
            w.println(name+":"+o.toString());
            w.println("<br>");}
        }
        w.println("<a href='userinfo.html'>返回首页</a>");
    }
}
