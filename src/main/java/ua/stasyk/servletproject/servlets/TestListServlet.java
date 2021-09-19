package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.models.Test;
import ua.stasyk.servletproject.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/testslist")
public class TestListServlet extends HttpServlet {
    private TestService testService = new TestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Test> tests  = testService.showTestList();
        request.setAttribute("tests",tests);
        request.getRequestDispatcher("testslist.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("deleteId") != null) {
            Integer testId = Integer.parseInt(request.getParameter("deleteId"));
            testService.delete(testId);
            response.sendRedirect("testslist");
        }else if(request.getParameter("editId") != null){
            Integer editId=Integer.parseInt(request.getParameter("editId"));
            session.setAttribute("editId",editId);
            response.sendRedirect("edittest");
        }
    }
}
