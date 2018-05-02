package com.servlet.user;

import com.dao.BookDao;
import com.entity.book;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/LendFiles.do")
public class LendFilesServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDao bd = new BookDao();
        User u =(User) request.getSession().getAttribute("user");
        String id = u.getReader_id();
        book b = bd.loadByid(id);
        if(b!=null){
            request.setAttribute("book",b);
            request.getRequestDispatcher("/html/LendFile.html").forward(request,response);
        }else{
            response.sendRedirect("fal.do");
        }
    }
}
