package org.example.controller;

import org.example.model.Inventory;
import org.example.model.UserInfo;
import org.example.service.InventoryService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserInfoService userInfoService;
    private InventoryService inventoryService;

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam("username")String username,
                            @RequestParam("password")String password,
                            @RequestParam("role")String role, Model model) {

        //Validating username, password and the role

        if("userAdmin".equals(username) && "adminPass".equals(password) && "Admin".equals(role)) {
            List<UserInfo> userInfos = UserInfoService.getAllUsers();
            model.addAttribute("userInfos", userInfos);
            return "adminDashboard";
        }else {
            model.addAttribute("error","Invalid username, password or role");

            return "loginAdmin";
        }

        }
    @GetMapping("/removeUserInfos")
    public String removeUserInfos(@RequestParam("user_id") long user_id, Model model) {

        try {
            userInfoService.deleteUserInfos(user_id);  // Call the method using EntityManager
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        // Redirect back to the admin dashboard to see updated data
        return "redirect:/adminDashboard";
    }

    @GetMapping("/inventory")
    public String showInventoryDetails(Model model) {
        List<Inventory> inventoryList =inventoryService.getAllInventory();
        model.addAttribute("inventoryList", inventoryList);

        return "inventory";
    }

}
