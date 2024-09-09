import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());

        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveUserInfo(@ModelAttribute("userInfo") UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return "success";
    }
}
