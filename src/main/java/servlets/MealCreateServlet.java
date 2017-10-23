package servlets;

import model.Meal;
import model.User;
import repository.jdbc.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealCreateServlet extends HttpServlet {
    private MealDAO mealDaoImpl = new MealDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        Meal meal = new Meal(user, LocalDateTime.parse(req.getParameter("dateTime")),
            req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
        try {
            mealDaoImpl.insertMeal(meal);
        } catch (MealDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meals").forward(req, resp);
//        resp.sendRedirect(String.format(req.getContextPath(), "/meals"));
    }
}
