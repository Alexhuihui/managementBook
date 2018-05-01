package com.servlet.ReaderInfo;

import com.entity.ReaderInfo;
import com.utils.DateTransform2;
import com.utils.JdbcMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/select2")
public class select2Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        List<ReaderInfo> reader = new ArrayList<>();
        String SQL = "select * from reader_info";
        PreparedStatement ps = JdbcMysql.CreatePs(SQL);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                ReaderInfo r = new ReaderInfo();
                r.setReader_id(rs.getString("reader_id"));
                r.setName(rs.getString("name"));
                r.setPasswd(rs.getString("passwd"));
                r.setSex(rs.getString("sex"));
                r.setBirth(DateTransform2.dToS(rs.getDate("birth")));
                r.setAddress(rs.getString("address"));
                r.setTelcode(rs.getString("telcode"));
                reader.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession se=request.getSession();
        se.setAttribute("readerList",reader);
        request.getRequestDispatcher("result.html").forward(request,response);

    }
}
