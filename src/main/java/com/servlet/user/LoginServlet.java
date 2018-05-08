package com.servlet.user;

import com.dao.CourseDao;
import com.dao.UserDao;
import com.entity.Course;
import com.entity.User;
import com.utils.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    UserDao ud = new UserDao() ;
    CourseDao cd = new CourseDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("name") ;
        String password = request.getParameter("passwd") ;
        String code = request.getParameter("code") ;
        String sessionCode = (String)request.getSession().getAttribute("code") ;
        if(StringHelper.empty( username )){
            request.setAttribute("message" , "请输入用户名");
        }else if(StringHelper.empty( password )){
            request.setAttribute("message" , "请输入密码");
        }else if(StringHelper.empty( code )){
            request.setAttribute("message" , "请输入验证码");
        }else if(!code.equalsIgnoreCase( sessionCode )){
            request.setAttribute("message" , "验证码错误");
        }else{
            User user = ud.loadByUsername( username );//比较数据库里的用户名
            if( user == null || (!StringHelper.encrypt( password ).equals(user.getPasswd()))) {
//            if( user == null || (! password .equals(user.getPasswd()))) {
                request.setAttribute("message" , "用户名或密码错误");
            }
            else if(username.equals("admin")){
                request.getSession().setAttribute("admin_id" , user.getReader_id() );
                // 获取所有的图书
                List<Course> book =  cd.loadAll() ;

                request.getSession().setAttribute("book" , book );
                request.getSession().setAttribute("user" , user );
                response.sendRedirect("pages/user/admin.html");
                return;
            }
            else {
                request.getSession().setAttribute("user" , user );
                response.sendRedirect("pages/user/user.html");
                return;
            }
        }
        request.getRequestDispatcher("pages/user/login.html").forward(request , response );
    }
}
