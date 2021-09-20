package ua.stasyk.servletproject.servlets;

import ua.stasyk.servletproject.models.User;
import ua.stasyk.servletproject.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        HttpSession session=request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        User user = userService.login(username,password);

        if(user==null){
            session.setAttribute("message",resourceBundle.getString("login.danger.alert"));
            response.sendRedirect("login");
        } else {
            if(user.isBlocked()==false) {
                session.setAttribute("user", username);
                session.setAttribute("role", user.getRole().getRole());
                response.sendRedirect("index");
            }else {
                session.setAttribute("message",resourceBundle.getString("login.danger.alert.blocked"));
                response.sendRedirect("login");
            }
        }
    }
}
