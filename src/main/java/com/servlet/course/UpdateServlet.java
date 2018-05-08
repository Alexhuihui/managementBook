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

@WebServlet("/course/update.do")
public class UpdateServlet extends HttpServlet {
    CourseDao cd = new CourseDao() ;
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
        // 根据id 加载一个Course对象？
        Course c = cd.loadById(Book_id);
        c.setName(name);
        c.setAuthor(author);
        c.setAuthor(author);
        c.setPublish(publish);
        c.setISBN(ISBN);
        c.setIntroduction(introduction);
        c.setLanguage(language);
        c.setPrice(price);
        c.setPubdate(pubdate);
        c.setPubdate(pubdate);
        c.setClass_id(class_id);
        c.setNumber(number);
         boolean b = cd.update( c ) ;//将c里内容传到数据库中
         if( b ){
             List<Course> book =  cd.loadAll() ;//如果成功了，就将cd 里的内容放在一个list 里 ，再通过session传到前端页面

             request.getSession().setAttribute("book" , book );
             response.sendRedirect("/pages/courses/courseList.html");
         }else{
             response.sendRedirect("/pages/error/500.html");
         }

    }
}
