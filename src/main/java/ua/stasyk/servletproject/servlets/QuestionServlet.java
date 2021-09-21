package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.services.OptionService;
import ua.stasyk.servletproject.services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {

    private QuestionService questionService= new QuestionService();
    private OptionService optionService = new OptionService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("question.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String questionText = request.getParameter("questionText");
        String subjectName = (String) session.getAttribute("subjectName");
        if(subjectName==null){
            ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
            session.setAttribute("message",resourceBundle.getString("question.danger.alert"));
            response.sendRedirect("createtest");
        }else {
            questionService.create(questionText, subjectName);
            session.setAttribute("questionText", questionText);
            String questionName = (String) session.getAttribute("questionText");
            String option1 = request.getParameter("option1");
            Boolean option1isCorrect = Boolean.parseBoolean(request.getParameter("option1isCorrect"));
            optionService.create(option1, option1isCorrect, questionName);
            String option2 = request.getParameter("option2");
            Boolean option2isCorrect = Boolean.parseBoolean(request.getParameter("option2isCorrect"));
            optionService.create(option2, option2isCorrect, questionName);
            String option3 = request.getParameter("option3");
            Boolean option3isCorrect = Boolean.parseBoolean(request.getParameter("option3isCorrect"));
            optionService.create(option3, option3isCorrect, questionName);
            String option4 = request.getParameter("option4");
            Boolean option4isCorrect = Boolean.parseBoolean(request.getParameter("option4isCorrect"));
            optionService.create(option4, option4isCorrect, questionName);
            response.sendRedirect("question");
        }
    }
}
