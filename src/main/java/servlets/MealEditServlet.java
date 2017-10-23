package servlets;//package servlets;

import model.Meal;
import model.User;
import repository.jdbc.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealEditServlet extends HttpServlet {
    private MealDAO mealDaoImpl = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("meal", mealDaoImpl.getMealByIdAndUser(user, Integer.parseInt(req.getParameter("id"))));
        } catch (MealDAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editMeal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            User user = (User) req.getSession().getAttribute("user");
            Meal meal = new Meal(user, LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
            mealDaoImpl.updateMealById(meal, Integer.parseInt(req.getParameter("id")));
        } catch (MealDAOException e) {
            e.printStackTrace();
        }
//        req.getRequestDispatcher("/meals").forward(req, resp);
        resp.sendRedirect("/meals");

    }
}
