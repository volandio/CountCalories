package servlets;//package servlets;

import repository.jdbc.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealDeleteServlet extends HttpServlet {
    private MealDAO mealDaoImpl = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            mealDaoImpl.deleteMealById(Integer.valueOf(req.getParameter("id")));
        } catch (MealDAOException e) {
            e.printStackTrace();
        }
//        resp.sendRedirect(String.format(req.getContextPath(), "meals"));
        req.getRequestDispatcher("/meals").forward(req, resp);
    }
}
