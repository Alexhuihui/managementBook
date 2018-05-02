package com.servlet.user;

import com.dao.UserDao;
import com.entity.User;
import com.utils.StringHelper;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebServlet("/Update.do")
public class UpdateServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao ud = new UserDao();
        String p1 = request.getParameter("npassword");
        String id = request.getParameter("id");
        String p2 = request.getParameter("rnpassword");
        if(StringHelper.empty( p1 )||StringHelper.empty(p2)) {
            request.setAttribute("message", "密码不能为空");
        }else if(p1.length() < 5 || p1.length() > 15 || (!p1.matches("[A-Za-z0-9]+"))) {
            request.setAttribute("message", "密码长度在5~15位之间,且仅包含数字和字母");
        }else if(!p1.equals(p2)) {
            request.setAttribute("message", "两次密码不一致");
        }else {
            boolean b = ud.updateu(StringHelper.encrypt( p1 ), id);
            if (b) {
                response.sendRedirect("upsucess.do");
            }
        }
        request.getRequestDispatcher("html/updatep.html").forward(request , response );
    }
}
