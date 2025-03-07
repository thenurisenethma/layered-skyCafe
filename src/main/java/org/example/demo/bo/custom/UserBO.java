package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;

public interface UserBO extends SuperBO {
    boolean authenticateUser(String username, String password);

    boolean saveUser(String userId, String username, String password, String role);
}
