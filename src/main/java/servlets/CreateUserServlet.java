package servlets;

import model.User;
import repository.jdbc.*;
import services.RegistrationService;
import services.RegistrationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserServlet extends HttpServlet {
    private UserDAO userDAOImpl = new UserDAOImpl();

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
        try {
            for (User user : userDAOImpl.getAllUsers()) {
                if (user.getEmail().equals(req.getParameter("email"))) {
                    req.setAttribute("message", "Пользователь с таким Email уже зарегестрирован!");
                    req.getRequestDispatcher("/createUser.jsp").forward(req, resp);
                    this.destroy();
                }
            }
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        registrationService.regUser(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));
        resp.sendRedirect("/");

    }
}
