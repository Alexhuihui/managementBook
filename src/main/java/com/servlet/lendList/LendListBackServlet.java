package com.servlet.lendList;

import com.entity.LendList;
import com.service.LendService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/lend/back/operator")
public class LendListBackServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//        writer.println("test successful");

        LendService service = new LendService();

        long lendId = Long.parseLong(request.getParameter("sernum"));
        boolean success = service.update(lendId);
        if(success){
//            response.sendRedirect("/pages/lendList/lendListShow.html");
            List<LendList> lendArrayList = service.select();
            request.setAttribute("lends", lendArrayList);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/lendList/lendListShow.html");
            rd.forward(request, response);
        }else{
            System.out.println("不能归还");
            List<LendList> lendArrayList = service.select();
            request.setAttribute("lends", lendArrayList);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/lendList/lendListShow.html");
            rd.forward(request, response);
        }
    }
}
