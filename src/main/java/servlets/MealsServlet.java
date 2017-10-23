package servlets;

import model.Meal;
import model.MealWithExceed;
import model.User;
import repository.jdbc.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static util.MealsUtil.getFilteredWithExceeded;


public class MealsServlet extends HttpServlet {
    private MealDAO mealDaoImpl = new MealDaoImpl();
    private UserDAO userDAOImpl = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String temp = (String) req.getSession().getAttribute("email");
        try {
            User user = userDAOImpl.getUserByEmail(temp);
            List<Meal> mealsList = mealDaoImpl.getAllMealsByUser(user);
            List<MealWithExceed> mealsWithExceedList = getFilteredWithExceeded(mealsList, LocalTime.of(00, 00),
                LocalTime.of(23, 59), 2000);
            req.setAttribute("list", mealsWithExceedList);
//            req.setAttribute("list", mealsList);
        } catch (UserDAOException | MealDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = (String) req.getSession().getAttribute("email");
        try {
            User user = userDAOImpl.getUserByEmail(email);
            System.out.println(req.getAttribute("maxSumCalories"));
            if (req.getParameter("maxSumCalories") != null) {
                int maxSumCalories = Integer.parseInt(req.getParameter("maxSumCalories"));
                user.setCaloriesPerDay(maxSumCalories);
            }
            req.getSession().setAttribute("user", user);
            List<Meal> mealsList = mealDaoImpl.getAllMealsByUser(user);
            List<MealWithExceed> mealsWithExceedList = getFilteredWithExceeded(mealsList, LocalTime.of(00, 00),
                LocalTime.of(23, 59), user.getCaloriesPerDay());
            req.setAttribute("user", user);
            req.setAttribute("list", mealsWithExceedList);
//            req.setAttribute("list", mealsList);
        } catch (UserDAOException | MealDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}