package services;

import DB.DAO.UserDAOImpl;

public interface AuthorizationService {
    Boolean auth(String login, String password) throws UserDAOImpl.UserDAOException;
}
