package DB.DAO;

import DB.ConnectionManagerPostgreSQL;
import DB.IConnectionManager;
import model.Meal;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MealDao {
    public static class MealDAOException extends Exception {
    }

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }

    public static List<Meal> getAllMeals() throws MealDAOException {
        List<Meal> mealsList = new ArrayList<>();
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT u.id id, name, email, password, registered, " +
                "enabled, calories_per_day, m.id id_meal, user_id, date_time,description, calories FROM users u, " +
                "meals m WHERE u.id=m.user_id");
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getDate("registered"),
                    resultSet.getInt("calories_per_day")
                );
                LocalDateTime localDateTime = resultSet.getObject("date_time", LocalDateTime.class);
                Meal meal = new Meal(
                    resultSet.getInt("id_meal"),
                    user,
                    localDateTime,
                    resultSet.getString("description"),
                    resultSet.getInt("calories")
                );
                mealsList.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
        return mealsList;
    }

    public static List<Meal> getAllMealsByUser(User user) throws MealDAOException {
        List<Meal> mealsList = new ArrayList<>();
        try {
            int userId = user.getUserId();
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, date_time, description, calories FROM meals WHERE user_id = " + userId);
            while (resultSet.next()) {
                LocalDateTime localDateTime = resultSet.getObject("date_time", LocalDateTime.class);
                Meal meal = new Meal(
                    resultSet.getInt("id"),
                    user,
                    localDateTime,
                    resultSet.getString("description"),
                    resultSet.getInt("calories")
                );
                mealsList.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
        return mealsList;
    }

    public static Meal getMealById(int id) throws MealDAOException {
        Meal meal = null;
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT u.id id, name, email, password, registered, " +
                "enabled, calories_per_day, m.id id_meal, user_id, date_time,description, calories FROM users u, " +
                "meals m WHERE u.id=m.user_id AND m.id = " + id);
            User user = new User(
                resultSet.getInt("id"),
                resultSet.getBoolean("enabled"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getDate("registered"),
                resultSet.getInt("calories_per_day")
            );
            LocalDateTime localDateTime = resultSet.getObject("date_time", LocalDateTime.class);
            meal = new Meal(
                resultSet.getInt("id_meal"),
                user,
                localDateTime,
                resultSet.getString("description"),
                resultSet.getInt("calories")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
        return meal;
    }

    public static Meal getMealByIdAndUser(User user, int id) throws MealDAOException {
        Meal meal = null;
        try {
            PreparedStatement statement = manager.getConnection().
                prepareStatement("SELECT * FROM meals WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LocalDateTime localDateTime = resultSet.getObject("date_time", LocalDateTime.class);
                meal = new Meal(
                    id,
                    user,
                    localDateTime,
                    resultSet.getString("description"),
                    resultSet.getInt("calories")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
        return meal;
    }

    public static void updateMealById(Meal meal, int id) throws MealDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  meals SET date_time = ?," +
                " description = ?, calories = ? WHERE id = " + id);
            statement.setObject(1, meal.getDateTime());
            statement.setString(2, meal.getDescription());
            statement.setInt(3, meal.getCalories());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
    }

    public static void updateAllMeals(List<Meal> mealsList) throws MealDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  meals SET user_id = ?" +
                ", date_time = ?, description = ?, calories = ? WHERE id = ?");
            for (Meal meal : mealsList) {
                statement.setInt(1, meal.getUser().getUserId());
                statement.setObject(2, meal.getDateTime());
                statement.setString(3, meal.getDescription());
                statement.setInt(4, meal.getCalories());
                statement.setInt(5, meal.getIdMeal());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
    }

    public static void deleteMealById(int id) throws MealDAOException {
        try {
            Statement statement = manager.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM meals WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
    }

    public static void insertMeal(Meal meal) throws MealDAOException {
        try {
            PreparedStatement statement =
                manager.getConnection().prepareStatement("INSERT INTO meals (user_id, date_time, description, " +
                    "calories) VALUES(?, ?, ?, ?)");
            statement.setInt(1, meal.getUser().getUserId());
            statement.setObject(2, meal.getDateTime());
            statement.setString(3, meal.getDescription());
            statement.setInt(4, meal.getCalories());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
    }

    public static void insertAllMeals(List<Meal> mealsList) throws MealDAOException {
        try {
            PreparedStatement statement =
                manager.getConnection().prepareStatement("INSERT INTO meals (user_id, date_time, description, " +
                    "calories) VALUES(?, ?, ?, ?)");
            for (Meal meal : mealsList) {
                statement.setInt(1, meal.getUser().getUserId());
                statement.setObject(2, meal.getDateTime());
                statement.setString(3, meal.getDescription());
                statement.setInt(4, meal.getCalories());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MealDAOException();
        }
    }
}
