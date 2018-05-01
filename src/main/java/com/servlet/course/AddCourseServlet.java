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

@WebServlet("/course/add.do")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Book_id = request.getParameter("book_id") ;
        String name = request.getParameter("name") ;
        String author = request.getParameter("author") ;
        String publish = request.getParameter("publish") ;
        String ISBN = request.getParameter("ISBN") ;
        String introduction = request.getParameter("introduction") ;
        String language = request.getParameter("language") ;
        String price =request.getParameter("price");
        String pubdate = request.getParameter("pubdate") ;
        String class_id = request.getParameter("class_id") ;
        String number = request.getParameter("number") ;
        System.out.println(Book_id);
        Course c = new Course() ;
        c.setBook_id(Book_id);
        c.setName( name );
        c.setAuthor( author );
        c.setPublish( publish );
        c.setISBN( ISBN );
        c.setIntroduction( introduction);
        c.setLanguage( language );
        c.setPrice( price );
        c.setPubdate( pubdate );
        c.setClass_id( class_id );
        c.setNumber( number );
        c.setPublish( publish );

        CourseDao cd = new CourseDao() ;
        boolean b = cd.add( c ) ;
        if( b ){
            // 表示成功了
            request.getSession().setAttribute("course" , cd.loadAll());
            response.getWriter().print("<h1>添加图书成功，1秒之后自动回到图书管理页面</h1>") ;
            List<Course> book =  cd.loadAll() ;

            request.getSession().setAttribute("book" , book );
            response.setHeader("refresh", "1;url=/pages/courses/courseList.html");
        }else{
            // 失败
            response.getWriter().print("<h1>添加图书失败，1秒之后自动回到添加课程页面</h1>") ;
            response.setHeader("refresh", "1;url=/pages/courses/addCourse.html");
        }

    }
}
