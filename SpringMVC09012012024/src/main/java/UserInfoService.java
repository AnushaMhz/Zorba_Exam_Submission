import dao.UserInfoDAO;
import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Transactional
    public void saveUserInfo(UserInfo userInfo) {
        userInfoDAO.saveUserInfo(userInfo);
    }
}
