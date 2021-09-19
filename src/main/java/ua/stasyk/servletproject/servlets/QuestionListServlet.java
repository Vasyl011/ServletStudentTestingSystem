package ua.stasyk.servletproject.servlets;


import ua.stasyk.servletproject.models.Question;
import ua.stasyk.servletproject.services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/questionlist")
public class QuestionListServlet extends HttpServlet {

    private QuestionService questionService = new QuestionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = questionService.showQuestionList();
        request.setAttribute("questions",questions);
        request.getRequestDispatcher("questionlist.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer questionId = Integer.parseInt(request.getParameter("deleteId"));
        questionService.delete(questionId);
        response.sendRedirect("questionlist");
    }
}
