package com.servlet.lendList;

import com.dao.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/relend")
public class relend extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbDao dao = null;
        dao = new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/library","root","");
        try {
            dao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter w = response.getWriter();
        Date date = new Date(System.currentTimeMillis());
        String sernumb = request.getParameter("sernum");
        String sql = "update lend_list set lend_date = "+"'"+date+"'"+" where sernum = "+sernumb;
        try {
            dao.modify(sql);
            request.setAttribute("back","update");
            request.getRequestDispatcher("/lend").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
