package servlets;//package servlets;

import DB.DAO.MealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DB.DAO.MealDao.deleteMealById;

public class MealDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            deleteMealById(Integer.valueOf(req.getParameter("id")));
        } catch (MealDao.MealDAOException e) {
            e.printStackTrace();
        }
//        resp.sendRedirect(String.format(req.getContextPath(), "meals"));
        req.getRequestDispatcher("/meals").forward(req, resp);
    }
}
