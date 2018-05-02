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

@WebServlet("/register.do")
public class RegisterUserServlet extends HttpServlet {
     UserDao ud = new UserDao() ;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String username = request.getParameter("name") ;
        String password = request.getParameter("passwd") ;
        String rePassword = request.getParameter("repasswd") ;
        String telephone = request.getParameter("telcode");
        String code = request.getParameter("code") ;
        String sessionCode = (String) request.getSession().getAttribute("code") ;
        if(StringHelper.empty( username )){
            request.setAttribute("message" , "用户名不能为空");
        }else if(StringHelper.empty( password )||StringHelper.empty(rePassword)){
            request.setAttribute("message" , "密码不能为空");
        }else if(StringHelper.empty( code )){
            request.setAttribute("message" , "请输入验证码");
        }else if(!code.equalsIgnoreCase( sessionCode )){
            request.setAttribute("message" , "验证码错误");
        }else if(username.length() < 5 || username.length() > 16 || (!username.matches("[A-Za-z0-9]+"))) {
            request.setAttribute("message" , "用户名长度在5~16位之间,且仅包含数字和字母");
        }else if(password.length() < 5 || password.length() > 15 || (!password.matches("[A-Za-z0-9]+"))) {
            request.setAttribute("message", "密码长度在5~15位之间,且仅包含数字和字母");
        }else if(password.equals(rePassword)) {
            request.setAttribute("message", "两次密码不一致");
        }else if(!(telephone.length()==11 && telephone.matches("[0-9]+"))) {
            request.setAttribute("message", "电话号码输入有误");
        }else{
            User u = ud.loadByUsername( username ) ;
            if( u != null ) {
                request.setAttribute("message", "用户名已经被使用了");
            }else{
                u = new User() ;
                u.setReader_id(StringHelper.uuid() );
                u.setName(username );
                u.setPasswd(StringHelper.encrypt( password ));
                u.setTelcode(telephone);
                boolean result = ud.add(u) ;
                if( result ){
                    request.getSession().setAttribute("user" , u );
                    response.getWriter().print("<h1>注册成功，1秒之后自动跳转至用户页面</h1>") ;
                    response.setHeader("refresh", "1;url=pages/user/user.html");
                    return;
                }else{
                    response.getWriter().print("<h1>注册失败，1秒之后自动回到首页</h1>") ;
                    response.setHeader("refresh", "1;url=pages/user/book.com.html");
                    return;
                }
            }
        }
        request.getRequestDispatcher("pages/user/regist.html").forward(request , response );
    }
}
