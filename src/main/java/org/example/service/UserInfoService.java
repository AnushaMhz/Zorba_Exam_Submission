package org.example.service;

import org.example.controller.UserInfoController;
import org.example.dao.UserInfoDAO;
import org.example.model.Role;
import org.example.model.UserInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserInfoService {

    @PersistenceContext
    private  EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;
    private UserInfoDAO userInfoDAO;
    private UserInfoController userInfoController;

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


}
