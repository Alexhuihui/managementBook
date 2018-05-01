package com.servlet.course;

import com.dao.CourseDao;
import com.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/course/delete.do")
public class DeleteCourse extends HttpServlet {
    CourseDao cd = new CourseDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String book_id = request.getParameter("book_id");
        Course c = new Course() ;
        c.setBook_id( book_id );
        boolean b = cd.delete(c ) ;
        if( b ){
            // 删除成功
            List<Course> book =  cd.loadAll() ;
            request.getSession().setAttribute("book" , book );
            response.sendRedirect("/pages/courses/courseList.html");
        }else{
            // 删除失败
            response.sendRedirect("/pages/error/500.html");
        }
    }
}
