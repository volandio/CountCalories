package dao.jdbc;

import Jaxb.Meals;
import Jaxb.Users;
import dao.exceptions.jdbc.MealDAOException;
import dao.exceptions.jdbc.UserDAOException;
import model.Meal;
import model.User;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static Jaxb.JaxbUtil.marshalingExample;
import static Jaxb.JaxbUtil.unMarshalingExample;

public class Main {

    public static void main(String[] args) {
        MealDAO mealDaoImpl = new MealDaoImpl();
        UserDAO userDAOImpl = new UserDAOImpl();
        Users users = new Users();
        try {
            List<User> usersListJaxbOut = ((Users) unMarshalingExample("Users", "Jaxb.Users")).getUsersList();
            userDAOImpl.insertAllUsers(usersListJaxbOut);
            List<User> userListTableOut = userDAOImpl.getAllUsers();
            users.setUserslsList(userListTableOut);
            marshalingExample(users, "UsersFromTable");
        } catch (JAXBException | UserDAOException e) {
            e.printStackTrace();
        }

        List<Meal> mealsList = Arrays.asList(
            new Meal(users.getUsersList().get(0), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 10, 0), "Завтрак", 500),
            new Meal(users.getUsersList().get(0), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 13, 0), "Обед", 1000),
            new Meal(users.getUsersList().get(1), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 20, 0), "Ужин", 500)
        );

        Meals meals = new Meals();
        try {
            mealDaoImpl.insertAllMeals(mealsList);
            List<Meal> mealsListTableOut = mealDaoImpl.getAllMeals();
            meals.setMealsList(mealsListTableOut);
            marshalingExample(meals, "MealsFromTable");
        } catch (JAXBException | MealDAOException e) {
            e.printStackTrace();
        }
    }
}
