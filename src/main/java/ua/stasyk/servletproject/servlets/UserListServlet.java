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
import java.util.List;

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users  = userService.showUserList();
        request.setAttribute("users",users);
        request.getRequestDispatcher("userlist.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("unblocked") != null) {
            Integer userId= Integer.parseInt(request.getParameter("unblocked"));
            userService.unblockedUser(userId);
            response.sendRedirect("userlist");
        } else if (request.getParameter("blocked") !=null){
            Integer userId= Integer.parseInt(request.getParameter("blocked"));
            userService.blockedUser(userId);
            response.sendRedirect("userlist");
        }
    }
}
