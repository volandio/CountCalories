package services;

import dao.exceptions.jdbc.UserDAOException;

public interface AuthorizationService {
    Boolean auth(String login, String password) throws UserDAOException;
}
