package org.example.demo.bo.custom.impl;

import org.example.demo.bo.custom.UserBO;
import org.example.demo.dao.SuperDAO;
import org.example.demo.dao.custom.UserDAO;
import org.example.demo.dao.custom.impl.UserDAOImpl;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean authenticateUser(String username, String password) {
        return userDAO.checkLogin(username, password);
    }

    @Override
    public boolean saveUser(String userId, String username, String password, String role) {
        return userDAO.saveUser(userId,username,password,role);
    }
}
