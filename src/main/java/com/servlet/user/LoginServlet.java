package com.servlet.user;

import com.dao.UserDao;
import com.entity.User;
import com.utils.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    UserDao ud = new UserDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 声明几个boolean 变量 用于 假设
         */
        boolean isUsername = false  ; // 假设用户名不存在
        boolean isPassword = false ; // 密码错误
        boolean isCode = false ; // 验证码错误
        // 获取参数
        String username = request.getParameter("name") ;
        String password = request.getParameter("passwd") ;
        String code = request.getParameter("code") ;
        String scode = (String)request.getSession().getAttribute("code") ;
        // 根据用户名查数据库，是否存在
        User user = ud.loadByUsername( username ) ;
        if( user == null ){
            // 证明 没有这个用户名
            request.setAttribute("message" , "用户名或密码错误");
        }else{
            isUsername = true ;
            //  证明 有这个用户
            String encodePassword = StringHelper.encrypt( password ) ; // 获取 密码加密之后的 字符串
            if( user.getPasswd().equals(encodePassword )){
                // 证明 密码相同
                isPassword = true ;
                // 验证码相同
                if( code.equalsIgnoreCase( scode )){
                    // 验证码相同
                    isCode = true ;
                }else{
                    // 验证码错误
                    request.setAttribute("message" , "验证码错误");
                }
            }else{
                // 输入的密码错误
                request.setAttribute("message" , "用户名或密码错误");
            }
        }
        if( isCode && isPassword && isUsername){
            if(username.equals("admin")){
                response.sendRedirect("pages/user/user.html");
            }
            request.getSession().setAttribute("user" , user );
            response.sendRedirect("pages/user/user.html");
        }else{
            // 返回到登录页面
            request.getRequestDispatcher("pages/user/login.html").forward(request , response );
        }
    }
}
