package com.servlet.ReaderInfo;

import com.entity.ReaderInfo;
import com.utils.JdbcMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insertServlet")
public class InsertServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String reader_id=request.getParameter("reader_id");
        String name=request.getParameter("name");
        String passwd=request.getParameter("passwd");
        String sex=request.getParameter("sex");

        String birth=request.getParameter("birth");

        String country=request.getParameter("country");
        String province=request.getParameter("province");
        String address=country+province;

        String telcode=request.getParameter("telcode");

        ReaderInfo user=new ReaderInfo();
        user.setReader_id(reader_id);
        user.setName(name);
        user.setPasswd(passwd);
        user.setSex(sex);
        user.setBirth(birth);
        user.setAddress(address);
        user.setTelcode(telcode);

        JdbcMysql.InsertTable(user);
        PrintWriter w=response.getWriter();
        w.print("<h2 style='text-align:center'>添加成功,3秒后跳转回读者信息操作界面</h2>");
        response.setHeader("refresh","3,url=/userinfo.html");


    }
}
