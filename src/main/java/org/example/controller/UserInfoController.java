package org.example.controller;

import org.example.model.Role;
import org.example.model.UserInfo;
import org.example.service.RoleService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserInfoController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserInfoService userService;

    @RequestMapping(value = "/userForm", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "userForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("mobile") String mobile,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model) {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setEmail(email);
            userInfo.setMobile(mobile);
            userInfo.setUsername(username);
            userInfo.setPassword(password);

            // Save user with validation
            userService.saveUserInfo(userInfo);
            return "redirect:userForm/viewUsers"; // Redirect to the user view page after saving
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "userForm"; // Return to the form page with error message
        }
    }
    @RequestMapping("/viewUsers")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "viewUser";
    }
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<UserInfo> users = userService.getAllUsers();
        model.addAttribute("userInfo" ,users);
        return "viewUser";
    }

    @PostMapping("/assignRole")
    public String assignRole(@RequestParam("userId")int userId,
                                @RequestParam("roleName") String roleName) throws Exception {
    UserInfo userInfo = userService.getUserById(userId);
        Role role = userService.getOrCreateRole(roleName);

        userInfo.addRole(role);
        userService.saveUserInfo(userInfo);

        return "redirect:/viewUsers";
    }
    @GetMapping("/removeRole")
    @ResponseBody
    public String removeRole(@RequestParam int userId, @RequestParam String roleName) {
        roleService.removeRoleFromUser(userId, roleName);
        return "Role " + roleName + " removed from user ID " + userId;
    }
    @GetMapping("/userRoles")
    @ResponseBody
    public List<String> getUserRoles(@RequestParam int userId) {
        return roleService.findRolesByUserId(userId);
    }
    public boolean hasAdminAccess(int userId) {
        List<String> roles = roleService.findRolesByUserId(userId);
        return roles.contains("ADMIN");
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Perform login logic
        UserInfo userInfo = userService.authenticateUser(username, password); // Method to authenticate user

        if (userInfo != null) {
            // Assuming user is authenticated, get user details
            Long userId =userInfo.getUserId (); // Fetch user ID
            String userType = userInfo.getUserType(); // Fetch user type (Admin, Customer, Vendor)

            // AOP will handle the logging automatically (login time is captured in the aspect)
            return "redirect:/home"; // Redirect to home page
        } else {
            // Authentication failed, redirect to error page
            return "redirect:/login?error=true"; // Redirect with error parameter
        }
    }

    @PostMapping("/logout")
    public String logout() {
        // Perform logout logic
        // You might want to invalidate the session here if using sessions
        return "redirect:/login"; // Redirect to login page
    }

}
