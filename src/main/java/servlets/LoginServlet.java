package servlets;

import dao.exceptions.jdbc.UserDAOException;
import dao.jdbc.UserDAO;
import dao.jdbc.UserDAOImpl;
import services.AuthorizationService;
import services.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private AuthorizationService as = new AuthorizationServiceImpl();
    private UserDAO userDAOImpl = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            if (as.auth(email, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                session.setAttribute("isAuth", true);
                if (userDAOImpl.getUserByEmail(email).getAdmin()) {
                    req.getRequestDispatcher("/administrator").forward(req, resp);
                    return;
                }
                req.getRequestDispatcher("/meals").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/").forward(req, resp);
            }
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
    }
}
