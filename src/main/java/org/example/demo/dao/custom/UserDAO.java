package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean checkLogin(String username, String password);

    boolean saveUser(String userId, String username, String password, String role);
}
