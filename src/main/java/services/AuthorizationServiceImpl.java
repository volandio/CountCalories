package services;

import dao.exceptions.jdbc.UserDAOException;
import dao.jdbc.UserDAOImpl;
import model.User;

public class AuthorizationServiceImpl implements AuthorizationService {
    private UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public Boolean auth(String email, String password) throws UserDAOException {
        if (email == null || password == null) {
            return false;
        }
        User user = userDAOImpl.getUserByLoginAndPassword(email, PasswordEncoder.encode(password));
        if (user != null) {
            return true;
        }
        return false;
    }
}
