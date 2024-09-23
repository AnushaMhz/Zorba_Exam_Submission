package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class UserAuditService {
    @Autowired
    private DataSource dataSource;

    public void logLoginDetails(int userId, String userName, String userType) {
        String sql = "INSERT INTO user_login_audit (user_id, user_name, user_type, login_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, userName);
            stmt.setString(3, userType);
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logLogoutDetails(int userId) {
        String sql = "UPDATE user_login_audit SET logout_time = ? WHERE user_id = ? AND logout_time IS NULL";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logExecutionDetails(String controllerName, String methodName, long executionTime) {
        String sql = "INSERT INTO execution_audit_check (name_of_controller, name_of_method, execution_time) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, controllerName);
            stmt.setString(2, methodName);
            stmt.setLong(3, executionTime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
