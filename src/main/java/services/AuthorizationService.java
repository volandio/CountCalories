package services;

import repository.jdbc.UserDAOException;

public interface AuthorizationService {
    Boolean auth(String login, String password) throws UserDAOException;
}
