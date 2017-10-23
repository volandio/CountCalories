package repository.jdbc;

import model.Meal;
import model.User;

import java.util.List;

public interface MealDAO {
    List<Meal> getAllMeals() throws MealDAOException;

    List<Meal> getAllMealsByUser(User user) throws MealDAOException;

    Meal getMealById(int id) throws MealDAOException;

    Meal getMealByIdAndUser(User user, int id) throws MealDAOException;

    void updateMealById(Meal meal, int id) throws MealDAOException;

    void updateAllMeals(List<Meal> mealsList) throws MealDAOException;

    void deleteMealById(int id) throws MealDAOException;

    void insertMeal(Meal meal) throws MealDAOException;

    void insertAllMeals(List<Meal> mealsList) throws MealDAOException;
}
