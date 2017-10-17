package DB.DAO;

import model.User;


public interface UserDAO {
    User getUserByLoginAndPassword(String email, String password) throws UserDAOImpl.UserDAOException;
    Boolean createUser(User user);
}
