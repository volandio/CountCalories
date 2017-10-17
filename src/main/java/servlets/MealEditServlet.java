package servlets;//package servlets;

import DB.DAO.MealDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static DB.DAO.MealDao.getMealById;
import static DB.DAO.MealDao.updateMealById;

public class MealEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("meal", getMealById(Integer.valueOf(req.getParameter("id"))));
        } catch (MealDao.MealDAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editMeal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        resp.setCharacterEncoding("cp1251");
        try {
            updateMealById(getMealById(Integer.valueOf(req.getParameter("id"))), Integer.valueOf(req.getParameter("id")));
        } catch (MealDao.MealDAOException e) {
            e.printStackTrace();
        }
//        updateStudent(new Student((short) (int) Integer.valueOf(req.getParameter("id")), req.getParameter("firstName"),
//            req.getParameter("secondName"), req.getParameter("familyName"),
//            LocalDate.parse(req.getParameter("bdate"))));
        resp.sendRedirect("meals");
//        resp.sendRedirect("/meals");
//        resp.sendRedirect(String.format(req.getContextPath(), "/students"));
    }
}
