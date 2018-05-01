package com.servlet.course;

import com.dao.CourseDao;
import com.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/load.do")
public class LoadCourseServlet extends HttpServlet{
    CourseDao cd = new CourseDao() ;
    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {
        String book_id = request.getParameter("book_id") ;
        Course c = cd.loadById( book_id ) ;
        if( c != null ){
            request.getSession().setAttribute("c" , c );
            response.sendRedirect("/pages/courses/update.html");
        }else{
            // 没有获取到
            response.sendRedirect("/pages/error/500.html");
        }
    }
}
