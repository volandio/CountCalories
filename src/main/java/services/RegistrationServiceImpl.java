package services;

import repository.jdbc.UserDAOImpl;
import model.User;

import static services.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {
    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public Boolean regUser(String name, String email, String password) {
        if (name == null || email == null || password == null) {
            return false;
        }
        return userDAOImpl.createUser(new User(name, email, encode(password)));
    }
}
