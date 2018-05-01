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
        // 获取参数
        String username = request.getParameter("name") ;
        String password = request.getParameter("passwd") ;
        String rePassword = request.getParameter("repasswd") ;
        String telephone = request.getParameter("telcode");
        String code = request.getParameter("code") ;
        //  从session中获取验证码
        String sessionCode = (String) request.getSession().getAttribute("code") ;
        /*
         * 声明几个boolean 变量 用于 假设
         */
        boolean isUsername = false  ; // 假设用户名有人用
        boolean isPassword = false ; // 密码不符合要求
        boolean isCode = false ; // 验证码 不符合要求
        boolean isTelephone = false; // 电话号码不符合要求
        // 判断 用户名是否为空
        if(StringHelper.empty( username )){
            // 表示 username 是空的 ，也就是没有输入，返回到注册页面进行提示
            request.setAttribute("message" , "用户名不能为空");
        }else{
            // 验证长度啥的
            if( username.length() < 5 || username.length() > 16 || (!username.matches("[A-Za-z0-9]+"))){
                // 用户名长度不在 6~18位之间，返回注册页面进行提示
                request.setAttribute("message" , "用户名长度在5~16位之间,且仅包含数字和字母");
            }else{
                // 表示 用户名 是 6~18位之间，并且不是空的
                // 用户名是否在数据库中存在
                // 1、 查询一次（ 根据 用户名查 id ）
                // 2、判断id 是否存在，如果存在即认为用户名有人用了 ， 返回到注册页面进行提示
                User u = ud.loadByUsername( username ) ;
                if( u != null ){
                    //  如果存在，就返回到注册页面进行提示
                    request.setAttribute("message" , "用户名已经被使用了");
                }else {
                    isUsername = true; // 推翻假设，认为是可用的
                    // 密码是否为空
                    if (StringHelper.notEmpty(password)) {
                        // 表示 用户名是可用的
                        // 密码 长度 6~18位
                        if (password.length() < 5 || password.length() > 15 || (!username.matches("[A-Za-z0-9]+"))) {
                            // 密码长度过长或过短 ： 返回到注册页面进行提示
                            request.setAttribute("message", "密码长度在5~15位之间,且仅包含数字和字母");
                        } else {
                            // 判断两次密码是否一致
                            if (password.equals(rePassword)) {
                                // 两个密码一致
                                isPassword = true;  // 表示 密码是可以使用的
                                // 验证码
                                if (code.toLowerCase().equalsIgnoreCase(sessionCode)) {
                                    // 证明验证码一致
                                    isCode = true; // 表示验证码没有问题
                                    if(telephone.length()==11 && telephone.matches("[0-9]+")){
                                        isTelephone = true;
                                    }
                                    else {
                                        request.setAttribute("message", "电话号码输入有误");
                                    }
                                } else {
                                    //  返回注册页面进行提示
                                    request.setAttribute("message", "验证码输入有误");
                                }
                            } else {
                                // 两次密码不一致，返回注册页面进行提示
                                request.setAttribute("message", "两次密码不一致");
                            }
                        }
                    }else{
                        request.setAttribute("message" ,"密码不能为空");
                    }
                }
            }
        }
        if( isCode && isPassword && isUsername && isTelephone){
            // 注册
            User u = new User() ;
            u.setReader_id(StringHelper.uuid() );
            u.setName(username );
            u.setPasswd(StringHelper.encrypt( password ));
            u.setTelcode(telephone);
            boolean result = ud.add(u) ;
            if( result ){
                response.getWriter().print("<h1>注册成功，3秒之后自动回到首页</h1>") ;
                response.setHeader("refresh", "3;url=pages/user/book.com.html");
            }else{
                response.getWriter().print("<h1>注册失败，3秒之后自动回到首页</h1>") ;
                response.setHeader("refresh", "3;url=pages/user/book.com.html");
            }
        }else{
            request.getRequestDispatcher("pages/user/regist.html").forward(request , response );
        }
    }
}
