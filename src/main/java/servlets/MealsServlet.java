package servlets;

import DB.DAO.MealDao;
import DB.DAO.UserDAOImpl;
import model.Meal;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static DB.DAO.MealDao.getAllMealsByUser;


public class MealsServlet extends HttpServlet {
    UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String temp = (String) req.getSession().getAttribute("email");
        try {
            User user = userDAO.getUserByEmail(temp);
            List<Meal> mealsList = getAllMealsByUser(user);
            req.setAttribute("list", mealsList);
        } catch (UserDAOImpl.UserDAOException | MealDao.MealDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userDAO.getUserByEmail(req.getParameter("email"));
            List<Meal> mealsList = getAllMealsByUser(user);
            req.setAttribute("list", mealsList);
        } catch (UserDAOImpl.UserDAOException | MealDao.MealDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}