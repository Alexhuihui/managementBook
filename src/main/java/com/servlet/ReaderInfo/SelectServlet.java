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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String reader_id=request.getParameter("reader_id");
        response.getWriter().println(reader_id);
        ReaderInfo user=new ReaderInfo();
        user.setReader_id(reader_id);

        Connection conn= JdbcMysql.getconn();
        response.getWriter().println(conn);
        String SQL="select * from reader_info where reader_id=?";
        PreparedStatement ps=JdbcMysql.CreatePs(SQL);
        response.getWriter().println(ps);
        List<ReaderInfo> reader=new ArrayList<>();
        ResultSet rs=null;
        try {
            ps.setObject(1,user.getReader_id());
            rs=ps.executeQuery();
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
