package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.models.Question;
import ua.stasyk.servletproject.models.StudentTest;
import ua.stasyk.servletproject.services.QuestionService;
import ua.stasyk.servletproject.services.TakeTestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/starttest")
public class StartTestServlet extends HttpServlet {

    private QuestionService questionService = new QuestionService();
    private TakeTestService takeTestService = new TakeTestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer testId = (Integer) session.getAttribute("testId");
        List<Question> questions = questionService.showQuestionListByTestId(testId);
        request.setAttribute("questions",questions);
        List<StudentTest> studentTests = takeTestService.showStudentTestListByTestId(testId);
        request.setAttribute("studentTests",studentTests);
        request.getRequestDispatcher("starttest.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer studentTestId = Integer.parseInt(request.getParameter("studentTestId"));
        Float result= null;
        String[] answers = request.getParameterValues("optionId");
        for (int i = 0; i < answers.length ; i++) {
            Integer answer =Integer.parseInt((answers[i]));
            result=takeTestService.testResult(answer);
        }
        takeTestService.edit(result,studentTestId);

        response.sendRedirect("personaloffice");
    }
}
