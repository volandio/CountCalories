package services;

import DB.DAO.UserDAOImpl;

public class AuthorizationServiceImpl implements AuthorizationService {
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public Boolean auth(String email, String password) throws UserDAOImpl.UserDAOException {
        if (email == null || password == null) {
            return false;
        }
        if (userDAOImpl.getUserByLoginAndPassword(email, PasswordEncoder.encode(password)) != null) {
            return true;
        }
        return false;
    }
}
