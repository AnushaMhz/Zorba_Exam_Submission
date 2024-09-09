package org.example.dao;

import org.example.model.Role;
import org.example.model.UserInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO {

    private SessionFactory sessionFactory;
    @Autowired
    public UserInfoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void saveUserInfo(UserInfo userInfo) {
        sessionFactory.getCurrentSession().save(userInfo);

    }
    public List<UserInfo> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from UserInfo", UserInfo.class)
                .list();
    }
    public UserInfo gerUserById(int userId) {
        return sessionFactory.getCurrentSession().get(UserInfo.class,userId);
    }

    public Role getRoleByName(String roleName) {
        String hql = "FROM Role WHERE roleName = :roleName";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Role.class)
                .setParameter("roleName", roleName)
                .uniqueResult();
    }

    public void saveRole(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }
}
