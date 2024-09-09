package dao;

import model.UserInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void saveUserInfo(UserInfo userInfo) {
        sessionFactory.getCurrentSession().save(userInfo);

    }


}
