package com.servlet.course;

import com.dao.CourseDao;
import com.dao.AdminDao;
import com.entity.Course;
import com.entity.Admin;
import com.utils.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/login.do")
public class LoginServlet extends HttpServlet {
    AdminDao ud = new AdminDao() ;
    CourseDao cd = new CourseDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 声明几个boolean 变量 用于 假设
         */
        boolean isAdmin_id = false  ; // 假设用户名不存在
        boolean isPassword = false ; // 密码错误
        // 获取参数
        String admin_id = request.getParameter("admin_id") ;
        String password = request.getParameter("password") ;
        // 根据admin_id查数据库，是否存在
        Admin Admin = ud.loadByAdmin_id( admin_id ) ;
        if( Admin == null ){
            // 证明 没有这个用户名
            request.setAttribute("message" , "用户名或密码错误");
        }else{
            isAdmin_id = true ;
            //  证明 有这个用户
//            String encodePassword = StringHelper.encrypt( password ) ; // 获取 密码加密之后的 字符串
            if( Admin.getPassword().equals(password)){
                // 证明 密码相同
                isPassword = true ;

            }else{
                // 输入的密码错误
                request.setAttribute("message" , "用户名或密码错误");
            }
        }
        if( isAdmin_id && isPassword ){
            // 进行登录操作
            // 跳转到主页面（所有课程的列表并放在session中）
            request.getSession().setAttribute("admin_id" , admin_id );
            // 获取所有的课程
            List<Course> book =  cd.loadAll() ;

            request.getSession().setAttribute("book" , book );
            // 重定向
            response.sendRedirect("/pages/courses/courseList.html");
        }else{
            // 返回到登录页面
            request.getRequestDispatcher("/pages/user/login.html").forward(request , response );
        }
    }
}
