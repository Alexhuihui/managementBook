package com.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 清空session
        HttpSession session = request.getSession() ;
        session.removeAttribute("user");
        session.invalidate(); // 废弃session
        response.sendRedirect("/book.com.html");
    }
}
