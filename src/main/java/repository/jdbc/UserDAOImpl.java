package repository.jdbc;

import repository.IConnectionManager;
import repository.SQLPoolConnection;
import model.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private IConnectionManager manager = new SQLPoolConnection();
    private static final Logger logger = Logger.getLogger(MealDaoImpl.class);

    static {
//        manager = ConnectionManagerPostgreSQL.getInstance();
        PropertyConfigurator.configure("D:\\Project\\INNOPOLIS\\CountCalories\\src\\main\\resources\\log4j.properties");
    }

    @Override
    public List<User> getAllUsers() throws UserDAOException {
        List<User> usersList = new ArrayList<>();
        logger.debug("log for getAllUsers");
        try (Statement statement = manager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
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
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
        return usersList;
    }

    @Override
    public User getUserByLoginAndPassword(String email, String password) throws UserDAOException {
        User user = null;
        logger.debug("log for getUserByLoginAndPassword");
        ResultSet resultSet = null;
        try (PreparedStatement statement = manager.getConnection().
            prepareStatement("SELECT * FROM users WHERE email = ? AND  password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
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
            logger.error(e.getMessage());
            throw new UserDAOException();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new UserDAOException();
            }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserDAOException {
        User user = null;
        logger.debug("log for getUserByEmail");
        ResultSet resultSet = null;
        try (PreparedStatement statement = manager.getConnection().
            prepareStatement("SELECT * FROM users WHERE email = ?")) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
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
            logger.error(e.getMessage());
            throw new UserDAOException();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new UserDAOException();
            }
        }
        return user;
    }

    @Override
    public Boolean createUser(User user) {
        logger.debug("log for createUser");
        try {
            insertUser(user);
            return true;
        } catch (UserDAOException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) throws UserDAOException {
        User user = null;
        logger.debug("log for getUserById");
        try (Statement statement = manager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id)) {
            user = new User(
                resultSet.getBoolean("enabled"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getDate("registered"),
                resultSet.getInt("calories_per_day"));
            user.setUserId(resultSet.getInt("id"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
        return user;
    }

    @Override
    public User updateUserById(User user, int id) throws UserDAOException {
        logger.debug("log for updateUserById");
        try (PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  users SET name = ?" +
            ", email = ?, password = ?, registered = ?, enabled = ?, calories_per_day = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, (Date) user.getRegistered());
            statement.setBoolean(5, user.getEnabled());
            statement.setInt(6, user.getCaloriesPerDay());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
        return user;
    }

    @Override
    public void updateAllUsers(List<User> usersList) throws UserDAOException {
        logger.debug("log for updateAllUsers");
        try (PreparedStatement statement = manager.getConnection().prepareStatement("UPDATE  users SET name = ?" +
            ", email = ?, password = ?, registered = ?, enabled = ?, calories_per_day = ? WHERE id = ?")) {
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
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException {
        logger.debug("log for deleteUser");
        try (Statement statement = manager.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
    }

    @Override
    public void insertUser(User user) throws UserDAOException {
        logger.debug("log for insertUser");
        try (PreparedStatement statement =
                 manager.getConnection().prepareStatement("INSERT INTO users (name, email, password, " +
                     "registered, enabled, calories_per_day) VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, new Date((user.getRegistered().getTime())));
            statement.setBoolean(5, user.getEnabled());
            statement.setInt(6, user.getCaloriesPerDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
    }

    @Override
    public void insertAllUsers(List<User> usersList) throws UserDAOException {
        logger.debug("log for insertAllUsers");
        try (PreparedStatement statement =
                 manager.getConnection().prepareStatement("INSERT INTO users (name, email, password, " +
                     "registered, enabled, calories_per_day) VALUES(?, ?, ?, ?, ?, ?)")) {
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
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
    }
}
