package org.example.controller;

import org.example.model.UserInfo;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/vendorLogin", method = RequestMethod.POST)
    public String vendorLogin(@RequestParam("username")String username,
                              @RequestParam("password") String password,
                              @RequestParam("role") String role,
                                Model model) {
        if(!"Vendor".equals(role)) {
            return "redirect:/inventory";
        }

        UserInfo userInfo = userInfoService.getUserByUsernameAndPassword(username,password);
        if(userInfo != null && "Vendor".equals(userInfo.getUsername())) {
            return "redirect:/inventory/add";
        }else {
            return "errorPage";
        }
    }
}
