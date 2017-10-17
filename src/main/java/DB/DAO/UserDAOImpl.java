package DB.DAO;

import DB.ConnectionManagerPostgreSQL;
import DB.IConnectionManager;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public static class UserDAOException extends Exception {
    }

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }

    public static List<User> getAllUsers() throws UserDAOException {
        List<User> usersList = new ArrayList<>();
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getDate("registered"),
                    resultSet.getInt("calories_per_day"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return usersList;
    }

    @Override
    public User getUserByLoginAndPassword(String email, String password) throws UserDAOException {
        User user = null;
        try {
            PreparedStatement statement = manager.getConnection().
                prepareStatement("SELECT * FROM users WHERE email = ? AND  password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getDate("registered"),
                    resultSet.getInt("calories_per_day"));
                user.setUserId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return user;
    }

    public User getUserByEmail(String email) throws UserDAOException {
        User user = null;
        try {
            PreparedStatement statement = manager.getConnection().
                prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getDate("registered"),
                    resultSet.getInt("calories_per_day"));
                user.setUserId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return user;
    }

    @Override
    public Boolean createUser(User user) {
        try {
            insertUser(user);
            return true;
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUserById(int id) throws UserDAOException {
        User user = null;
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id);
            user = new User(
                resultSet.getBoolean("enabled"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getDate("registered"),
                resultSet.getInt("calories_per_day"));
            user.setUserId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return user;
    }

    public static User updateUserById(User user, int id) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  users SET name = ?" +
                ", email = ?, password = ?, registered = ?, enabled = ?, calories_per_day = ? WHERE id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, (Date) user.getRegistered());
            statement.setBoolean(5, user.getEnabled());
            statement.setInt(6, user.getCaloriesPerDay());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
        return user;
    }

    public static void updateAllUsers(List<User> usersList) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  users SET name = ?" +
                ", email = ?, password = ?, registered = ?, enabled = ?, calories_per_day = ? WHERE id = ?");
            for (User user : usersList) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setDate(4, (Date) user.getRegistered());
                statement.setBoolean(5, user.getEnabled());
                statement.setInt(6, user.getCaloriesPerDay());
                statement.setInt(7, user.getUserId());
                statement.addBatch();
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public static void deleteUser(int id) throws UserDAOException {
        try {
            Statement statement = manager.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public static void insertUser(User user) throws UserDAOException {
        try {
            PreparedStatement statement =
                manager.getConnection().prepareStatement("INSERT INTO users (name, email, password, " +
                    "registered, enabled, calories_per_day) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, new Date((user.getRegistered().getTime())));
            statement.setBoolean(5, user.getEnabled());
            statement.setInt(6, user.getCaloriesPerDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public static void insertAllUsers(List<User> usersList) throws UserDAOException {
        try {
            PreparedStatement statement =
                manager.getConnection().prepareStatement("INSERT INTO users (name, email, password, " +
                    "registered, enabled, calories_per_day) VALUES(?, ?, ?, ?, ?, ?)");
            for (User user : usersList) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setDate(4, new Date((user.getRegistered().getTime())));
                statement.setBoolean(5, user.getEnabled());
                statement.setInt(6, user.getCaloriesPerDay());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }
}
