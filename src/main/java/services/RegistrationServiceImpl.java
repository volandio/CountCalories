package services;

import dao.jdbc.UserDAOImpl;
import model.User;

import static services.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public Boolean regUser(String name, String email, String password) {
        if (name == null || email == null || password == null) {
            return false;
        }
        User user = new User(name, email, encode(password));
        user.setEnabled(true);
        return userDAOImpl.createUser(user);
    }
}
