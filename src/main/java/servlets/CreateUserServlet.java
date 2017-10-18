package servlets;

import services.RegistrationService;
import services.RegistrationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationService registrationService = new RegistrationServiceImpl();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        registrationService.regUser(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));
        resp.sendRedirect("/");
    }
}
