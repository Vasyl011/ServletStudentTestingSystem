package ua.stasyk.servletproject.servlets;


import ua.stasyk.servletproject.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/createtest")
public class CreateTestServlet extends HttpServlet {
    private TestService testService = new TestService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createtest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String subjectName = request.getParameter("subjectName");
        String complexity =request.getParameter("complexity");
        Integer duration = Integer.parseInt(request.getParameter("duration"));
//        Integer numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
        testService.create(subjectName,complexity,duration);
        session.setAttribute("subjectName",subjectName);
        response.sendRedirect("question");
    }
}
