package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet ("/edittest")
public class EditTestServlet extends HttpServlet {
    private TestService testService = new TestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("edittest.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer testId = (Integer) session.getAttribute("editId");
        String subjectName = request.getParameter("subjectName");
        String complexity =request.getParameter("complexity");
        Integer duration = Integer.parseInt(request.getParameter("duration"));
        testService.edit(testId,subjectName,complexity,duration);
        response.sendRedirect("testslist");
    }
}
