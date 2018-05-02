package com.servlet.user;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/UserFiles.do")
public class UserFileservlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u =(User) request.getSession().getAttribute("user");

        if(u!=null){
            request.setAttribute("user",u);
            request.getRequestDispatcher("/html/UserFile.html").forward(request,response);
        }else{
            response.sendRedirect("index.html");
        }
    }
}
