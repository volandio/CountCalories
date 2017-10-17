package DB.DAO;

import Jaxb.Meals;
import Jaxb.Users;
import model.Meal;
import model.User;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static DB.DAO.MealDao.getAllMeals;
import static DB.DAO.MealDao.insertAllMeals;
import static DB.DAO.UserDAOImpl.getAllUsers;
import static DB.DAO.UserDAOImpl.insertAllUsers;
import static Jaxb.JaxbUtil.marshalingExample;
import static Jaxb.JaxbUtil.unMarshalingExample;

public class Main {
    public static void main(String[] args) {
        Users users = new Users();
        try {
            List<User> usersListJaxbOut = ((Users) unMarshalingExample("Users", "Jaxb.Users")).getUsersList();
            insertAllUsers(usersListJaxbOut);
            List<User> userListTableOut = getAllUsers();
            users.setUserslsList(userListTableOut);
            marshalingExample(users, "UsersFromTable");
        } catch (JAXBException | UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
        }

        List<Meal> mealsList = Arrays.asList(
            new Meal(users.getUsersList().get(0), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 10, 0), "Завтрак", 500),
            new Meal(users.getUsersList().get(0), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 13, 0), "Обед", 1000),
            new Meal(users.getUsersList().get(1), LocalDateTime.of(2017, Month.SEPTEMBER, 29, 20, 0), "Ужин", 500)
        );

        Meals meals = new Meals();
        try {
            insertAllMeals(mealsList);
            List<Meal> mealsListTableOut = getAllMeals();
            meals.setMealsList(mealsListTableOut);
            marshalingExample(meals, "MealsFromTable");
        } catch (JAXBException | MealDao.MealDAOException e) {
            e.printStackTrace();
        }
    }
}
