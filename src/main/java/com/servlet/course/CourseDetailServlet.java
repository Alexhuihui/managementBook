package com.servlet.course;

import com.dao.CourseDao;
import com.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/detail.do")
public class CourseDetailServlet extends HttpServlet {
    CourseDao cd = new CourseDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String book_id = request.getParameter("book_id") ;
//        System.out.println("book_id = " + book_id);
        Course c = cd.loadById( book_id ) ;
        if( c!= null ){
            request.getSession().setAttribute("c" , c );
            request.getRequestDispatcher("/pages/courses/detail.html").forward(request , response );
        }else{
            // 删除失败
            response.sendRedirect("/pages/error/500.html");
        }
    }
}
