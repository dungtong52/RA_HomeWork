package com.ra.repository;

import com.ra.model.User;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL insert_user(?, ?, ?, ?)}");
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.setDouble(4, user.getBalance());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return false;
    }
    public User findByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL find_user(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            rs = cs.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setBalance(rs.getDouble("balance"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return null;
    }
    public boolean existsByUsername(String username) {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL check_username_exists(?)}");
            cs.setString(1, username);
            rs = cs.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return false;
    }
}
