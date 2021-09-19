package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.models.Test;
import ua.stasyk.servletproject.services.TakeTestService;
import ua.stasyk.servletproject.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/choosetest")
public class ChooseTestServlet extends HttpServlet {

    private static final String testName = "Subject Name";

    private TestService testService = new TestService();
    private TakeTestService taketestService= new TakeTestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Test> tests =testService.showTestList();
        request.setAttribute("tests", tests);
        request.getRequestDispatcher("choosetest.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        if(request.getParameter("testId") != null) {
            Integer testId = Integer.parseInt(request.getParameter("testId"));
            session.setAttribute("testId", testId);
            String user = (String) session.getAttribute("user");
            session.setAttribute("user", user);

            taketestService.create(testId, user);
            response.sendRedirect("starttest");
        }else {
            String sort = request.getParameter("sort");
            if (sort.equals(testName)) {
                List<Test> tests = testService.showSortedTestListByName();
                request.setAttribute("tests", tests);
                request.getRequestDispatcher("choosetest.jsp").forward(request, response);
            } else {
                List<Test> tests = testService.showSortedTestListByComplexity();
                request.setAttribute("tests", tests);
                request.getRequestDispatcher("choosetest.jsp").forward(request, response);
            }
        }
    }
}
