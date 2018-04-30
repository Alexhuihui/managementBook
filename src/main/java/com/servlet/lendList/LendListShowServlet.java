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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/lend/back/show")
public class LendListShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LendService service = new LendService();
        List<LendList> lendArrayList = service.select();
        request.setAttribute("lends", lendArrayList);
        RequestDispatcher rd = request.getRequestDispatcher("/pages/lendList/lendListShow.html");
        rd.forward(request, response);
    }
}
