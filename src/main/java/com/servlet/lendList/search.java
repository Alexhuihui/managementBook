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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/search")
public class search extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select sernum ,book_id ,reader_id ,lend_date ,back_date ,is_back from lend_list order by lend_date desc";
        DbDao dao = null;
        try {
            dao = new DbDao("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "");
            dao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter w = response.getWriter();
        String book_id = request.getParameter("book_id");
        String reader_id = request.getParameter("reader_id");
        if(book_id.trim().length()<=0){
            request.setAttribute("back","no_inputbook");
            request.getRequestDispatcher("/lend").forward(request, response);
            return;
        }
        if(reader_id.trim().length()<=0){
            request.setAttribute("back","no_inputreader");
            request.getRequestDispatcher("/lend").forward(request, response);
            return;
        }
        sql = "select * from book_info where " + book_id + " in (SELECT book_id from book_info);";
        try {
            ResultSet rs = dao.query(sql);
            if (!rs.next()) {
                request.setAttribute("back","no_book");
                request.getRequestDispatcher("/lend").forward(request, response);
                return;
            } else {
                sql = "select number from book_info where book_id = "+book_id;
                rs = dao.query(sql);
                rs.next();
                if (rs.getInt(1) <= 0) {
                    request.setAttribute("back","back");
                    request.getRequestDispatcher("/lend").forward(request,response);
                    return;
                }else{
                    sql = "select * from reader_info where " +reader_id + " in (SELECT reader_id from reader_info);";
                    rs = dao.query(sql);
                    if(!rs.next()){
                        request.setAttribute("back","no_reader");
                        request.getRequestDispatcher("/lend").forward(request, response);
                        return;
                    }else {
                        Date date = new Date(System.currentTimeMillis());
                        sql = "insert into lend_list values(null," + book_id +","+reader_id+",'"+ date.toString() +"',null,0)";
                        boolean b = dao.insert(sql);
                        if(b){
                            request.setAttribute("back","lend");
                            request.getRequestDispatcher("/lend").forward(request, response);
                            return;
                        }
                    }
                }
            }
            } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

