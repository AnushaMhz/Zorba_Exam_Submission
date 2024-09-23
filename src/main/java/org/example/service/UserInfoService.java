package org.example.service;

import org.example.controller.UserInfoController;
import org.example.dao.UserInfoDAO;
import org.example.model.Inventory;
import org.example.model.InventoryCategory;
import org.example.model.Role;
import org.example.model.UserInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserInfoService {

    @Autowired
    private DataSource dataSource;
    @PersistenceContext
    private  EntityManager entityManager;

    @Autowired
    private UserInfoDAO userInfoDAO;
    private UserInfoController userInfoController;
    private SessionFactory sessionFactory;

    @Transactional
    public void deleteUserInfo(long userId) {
        UserInfo userInfo = entityManager.find(UserInfo.class, userId);
        if (userInfo != null) {
            entityManager.remove(userId);
        } else {
            throw new RuntimeException("User not found with Id: " + userId);
        }
    }

    @Transactional
    public void saveUserInfo(UserInfo userInfo) throws Exception {

        //email Validation
        if(!isValidEmail(userInfo.getEmail())) {

            throw  new Exception("Invalid Email. Email should contain @ Symbol");
        }

        //validate mobile number
        if(!isValidMobile(userInfo.getMobile())) {
            throw new Exception("Invalid Mobile number. Mobile number should be exactly 10 digits");
        }
        // validate password
        if(!isValidPassword(userInfo.getPassword())) {
            throw new Exception("Invalid Password. Password should be at least 8 characters.");
        }
        //if all validation pass, save user
        userInfoDAO.saveUserInfo(userInfo);
      sessionFactory.getCurrentSession().save(userInfo);
    }

    private  boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
    private boolean isValidMobile(String mobile) {
        return mobile !=null && mobile.length() == 10;
    }
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public static List<UserInfo> getAllUsers() {
        return getAllUsers();
    }
    private List<UserInfo> users = new ArrayList<>(); // Replace with actual database calls

    public UserInfo authenticateUser(String username, String password) {
        // Logic to authenticate the user (replace with database logic)
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null); // Return null if no user is found
    }

    public UserInfo getUserById(int userId) {
        return userInfoDAO.gerUserById(userId);
    }
    public UserInfo getUserByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", UserInfo.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Role getOrCreateRole(String roleName) {
        Role role = userInfoDAO.getRoleByName(roleName);
        if(role == null) {
            role = new Role();
            role.setRoleName(roleName);
            userInfoDAO.saveRole(role);
        }
        return role;
    }
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }


    public void removeRolesFromUser(int userId, int[] roleIds) {

    }
    public boolean validateUser(String username, String password, String role) {
        String query = "SELECT COUNT(*) FROM user_info WHERE username = ? AND password = ? AND role_id = (SELECT role_id FROM Role WHERE role_name = ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Fetch all inventory categories
    public List<InventoryCategory> findAllCategories() {
        List<InventoryCategory> categories = new ArrayList<>();
        String query = "SELECT category_id, category_name FROM inventory_category";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                InventoryCategory category = new InventoryCategory();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Fetch inventory items by category
    public List<Inventory> findInventoryByCategory(int categoryId) {
        List<Inventory> inventoryItems = new ArrayList<>();
        String query = "SELECT * FROM inventory WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Inventory item = new Inventory();
                item.setInventoryId(resultSet.getInt("inventory_id"));
                item.setInventoryName(resultSet.getString("inventory_name"));
                item.setQuantity(resultSet.getInt("inventory_quantity"));
                item.setPrice(resultSet.getDouble("inventory_price"));
                item.setImage(resultSet.getString("inventory_image"));
                item.setDescription(resultSet.getString("inventory_desc"));
                inventoryItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryItems;
    }

    // Add item to customer cart
    public void addToCart(int customerId, int inventoryId) {
        String query = "INSERT INTO customer_cart (customer_id, inventory_item_id) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            statement.setInt(2, inventoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

