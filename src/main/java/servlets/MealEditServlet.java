package servlets;//package servlets;

import DB.DAO.MealDao;
import model.Meal;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static DB.DAO.MealDao.getMealByIdAndUser;
import static DB.DAO.MealDao.updateMealById;

public class MealEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("meal", getMealByIdAndUser(user, Integer.parseInt(req.getParameter("id"))));
        } catch (MealDao.MealDAOException e) {
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
            updateMealById(meal, Integer.parseInt(req.getParameter("id")));
        } catch (MealDao.MealDAOException e) {
            e.printStackTrace();
        }
//        req.getRequestDispatcher("/meals").forward(req, resp);
        resp.sendRedirect("/meals");

    }
}
