package com.servlet.lendList;

import com.dao.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;

@WebServlet("/lend")
public class lend extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select sernum ,book_id ,reader_id ,lend_date ,back_date ,is_back from lend_list order by lend_date desc";
        DbDao dao = null;
        try {
            dao = new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/library","root","");
            dao.getConnection();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter w = response.getWriter();
        w.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>借书</title>\n" +
                "</head>\n" +
                "<body style=\"background-image: url('images/bgc2.jpg');background-size:100%,100%\">\n" +
                "<div style='margin-bottom:50px;margin-top:50px'>\n" +
                "    <form action=\"/search\" method=\"post\" style=\"text-align: center\">\n" +
                "        图书编号：<input type=\"text\" name=\"book_id\" style=\"width: 206px;height: 30px;\">\n" +
                "        读者编号：<input type=\"text\" name=\"reader_id\" style=\"width: 206px;height: 30px;\">\n" +
                "        <input type=\"submit\" value=\"借书\" name=\"search\" style=\"width: 50px;height: 36px;\">\n" +
                "    </form>\n" +
                "</div>\n" );
        ResultSet rs = null;
        int j = 0;
        try {
            rs = dao.query(sql);
            w.println("<div style=\"background-color: #fffddf;display:inline-block;margin-left: 320px;\">");
            w.println("<table border='1'cellspacing='0' align='center'cellpadding='10'>");
            w.println("<tr margin='0'>");
            w.println("<th>借书编号</th>");
            w.println("<th>图书编号</th>");
            w.println("<th>读者编号</th>");
            w.println("<th>借书时间</th>");
            w.println("<th>还书时间</th>");
            w.println("<th>是否归还(0代表未归还，1代表已归还)</th>");
            w.println("<th>操作</th>");
            w.println("</tr>");
            int num=0;
            while (rs.next()){
                int sernum = rs.getInt(1);
                int book_id = rs.getInt(2);
                int reader_id = rs.getInt(3);
                Date lend_date = rs.getDate(4);
                Date back_date = rs.getDate(5);
                int is_back = rs.getInt(6);
                w.println("<tr>");
                w.println("<td>"+sernum+"</td>");
                w.println("<td>"+book_id+"</td>");
                w.println("<td>"+reader_id+"</td>");
                w.println("<td>"+lend_date+"</td>");
                w.println("<td>"+back_date+"</td>");
                w.println("<td>"+is_back+"</td>");
                w.println("<td><a href='/relend?sernum="+sernum+"' style=\"text-decoration: none\">续借</a></td>");
                w.println("</tr>");
                j = j + 1;
                if(j == 10){
                    break;
                }
            }
            w.println("</table>");
            w.println("</div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        w.println("</body>\n" +
                "</html>");
        String back = (String) request.getAttribute("back");
        if(Objects.nonNull(back)) {
            if (back.equals("back")) {
                w.println("<script>alert(\"此书已被借光！\")</script>");
            } else if (back.equals("no_book")) {
                w.println("<script>alert(\"图书馆暂无此书！请选择其他书籍！\")</script>");
            } else if (back.equals("no_reader")) {
                w.println("<script>alert(\"无该读者，请让其前往注册！\")</script>");
            } else if (back.equals("lend")) {
                w.println("<script>alert(\"借书成功！\")</script>");
            } else if (back.equals("no_inputbook")) {
                w.println("<script>alert(\"请输入图书编号！\")</script>");
            } else if (back.equals("no_inputreader")) {
                w.println("<script>alert(\"请输入读者编号！\")</script>");
            } else if (back.equals("update")) {
                w.println("<script>alert(\"续借成功！\")</script>");
            }
        }
    }
}
