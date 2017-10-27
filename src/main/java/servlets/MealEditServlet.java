package servlets;//package servlets;

import dao.exceptions.jdbc.MealDAOException;
import dao.exceptions.jdbc.UserDAOException;
import model.Meal;
import model.User;
import dao.jdbc.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealEditServlet extends HttpServlet {
    private MealDAO mealDaoImpl = new MealDaoImpl();
    private UserDAO userDaoImpl = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        try {
            User user = userDaoImpl.getUserByEmail(email);
            req.setAttribute("meal", mealDaoImpl.getMealByIdAndUser(user, Integer.parseInt(req.getParameter("id"))));
        } catch (MealDAOException | UserDAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editMeal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = (String) req.getSession().getAttribute("email");
        try {
            User user = userDaoImpl.getUserByEmail(email);
            Meal meal = new Meal(user, LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
            mealDaoImpl.updateMealById(meal, Integer.parseInt(req.getParameter("id")));
        } catch (MealDAOException | UserDAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/meals");

    }
}
