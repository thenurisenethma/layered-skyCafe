package org.example.demo.dao.custom.impl;

import org.example.demo.dao.custom.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.demo.db.*;
import org.example.demo.entity.User;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getDbConnection().getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveUser(String userId, String username, String password, String role) {
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO user (user_id,username, password, role) VALUES (?,?,?,?)";
            PreparedStatement stmnt = connection.prepareStatement(sql);
            stmnt.setString(1, userId);
            stmnt.setString(2, username);
            stmnt.setString(3, password);
            stmnt.setString(4, role);
            int rowsInserted = stmnt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User DTO) throws SQLException, ClassNotFoundException {

        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
