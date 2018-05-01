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
import java.sql.Connection;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String reader_id=request.getParameter("reader_id");

        ReaderInfo user=new ReaderInfo();
        user.setReader_id(reader_id);
        Connection conn= JdbcMysql.getconn();
        JdbcMysql.deleteData(user);

        PrintWriter w=response.getWriter();
        w.print("<h2 style='text-align:center'>删除成功,3秒后跳转到读者信息操作界面</h2>");
        response.setHeader("refresh","3,url=/userinfo.html");
    }
}
