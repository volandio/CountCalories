package dao.jdbc;

import dao.exceptions.jdbc.UserDAOException;
import model.User;

import java.util.List;


public interface UserDAO {
    List<User> getAllUsers() throws UserDAOException;

    User getUserByLoginAndPassword(String email, String password) throws UserDAOException;

    User getUserByEmail(String email) throws UserDAOException;

    Boolean createUser(User user);

    User updateUserById(User user, int id) throws UserDAOException;

    void updateAllUsers(List<User> usersList) throws UserDAOException;

    void deleteUser(int id) throws UserDAOException;

    void insertUser(User user) throws UserDAOException;

    void insertAllUsers(List<User> usersList) throws UserDAOException;

    User getUserById(int id) throws UserDAOException;

    void changeCaloriesByUser(int userId, int calories);
}
