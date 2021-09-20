package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.models.StudentTest;
import ua.stasyk.servletproject.services.TakeTestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/personaloffice")
public class PersonalOfficeServlet extends HttpServlet {

    private TakeTestService takeTestService = new TakeTestService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username = (String) session.getAttribute("user");
        List<StudentTest> studentTests = takeTestService.showStudentTestListByUser(username);
        request.setAttribute("studentTests",studentTests);
        request.getRequestDispatcher("personaloffice.jsp").forward(request,response);
    }
}
