package services;

import repository.jdbc.UserDAOException;
import repository.jdbc.UserDAOImpl;

public class AuthorizationServiceImpl implements AuthorizationService {
    private UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public Boolean auth(String email, String password) throws UserDAOException {
        if (email == null || password == null) {
            return false;
        }
        if (userDAOImpl.getUserByLoginAndPassword(email, PasswordEncoder.encode(password)) != null) {
            return true;
        }
        return false;
    }
}
