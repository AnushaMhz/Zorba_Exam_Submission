package org.example.controller;

import org.example.service.RoleService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/removeRoles")
    public String removeRoles(@RequestParam("userId") int userId, @RequestParam("roles")int[] roleIds) {
        userInfoService.removeRolesFromUser(userId, roleIds);
        return "redirect:/viewUsers";
    }
}
