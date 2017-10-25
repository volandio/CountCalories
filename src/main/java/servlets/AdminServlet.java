package servlets;

import dao.exceptions.jdbc.UserDAOException;
import dao.jdbc.UserDAO;
import dao.jdbc.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private UserDAO userDAOImpl = new UserDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tempEmail = (String) request.getSession().getAttribute("email");
        User tempUser = null;
        try {
            tempUser = userDAOImpl.getUserByEmail(tempEmail);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        int idTempUser = tempUser.getUserId();
        if (id != idTempUser) {
            try {
                userDAOImpl.deleteUser(id);
            } catch (UserDAOException e) {
                e.printStackTrace();
            }
        }
        try {
            List<User> usersList = userDAOImpl.getAllUsers();
            request.setAttribute("users", usersList);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> usersList = userDAOImpl.getAllUsers();
            request.setAttribute("users", usersList);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
    }
}
