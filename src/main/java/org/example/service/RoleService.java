package org.example.service;

import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private DataSource dataSource;
    public void addRole(String roleName) {
        String query = "INSERT INTO Role (role_name) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roleName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all roles
    public List<String> findAllRoles() {
        List<String> roles = new ArrayList<>();
        String query = "SELECT role_name FROM Role";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                roles.add(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // Map user to a role
    public void assignRoleToUser(int userId, String roleName) {
        String query = "INSERT INTO User_Role (user_id, role_id) VALUES (?, (SELECT role_id FROM Role WHERE role_name = ?))";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, roleName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch roles for a specific user
    public List<String> findRolesByUserId(int userId) {
        List<String> roles = new ArrayList<>();
        String query = "SELECT r.role_name FROM User_Role ur JOIN Role r ON ur.role_id = r.role_id WHERE ur.user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                roles.add(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // Remove a role from a user
    public void removeRoleFromUser(int userId, String roleName) {
        String query = "DELETE FROM User_Role WHERE user_id = ? AND role_id = (SELECT role_id FROM Role WHERE role_name = ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, roleName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
