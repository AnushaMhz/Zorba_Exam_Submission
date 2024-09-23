package org.example.controller;

import org.example.model.Inventory;
import org.example.model.InventoryCategory;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/customerLogin", method = RequestMethod.POST)
    public String customerLogin(@RequestParam("username") String username,
                                @RequestParam("password")String password,
                                  @RequestParam("role")String role,
                                 Model model) {

        if(userInfoService.validateUser(username, password, role)) {
            return "redirect:/customerDashboard";

        }else {
            model.addAttribute("errorMessage", "Invalid credentials. Please try again!");
            return "CustomerLogin";
        }

    }
    @RequestMapping(value = "/customerDashboard", method = RequestMethod.GET)
    public String customerDashboard(Model model) {
        List<InventoryCategory> categories = userInfoService.findAllCategories();
        model.addAttribute("categories", categories);
        return "customerDashboard"; // Name of the dashboard JSP
    }
    @RequestMapping(value = "/getInventoryByCategory", method = RequestMethod.GET)
    public String getInventoryByCategory(@RequestParam("categoryId") int categoryId, Model model) {
        List<Inventory> inventoryItems = userInfoService.findInventoryByCategory(categoryId);
        model.addAttribute("inventoryItems", inventoryItems);
        return "customerDashboard"; // Same JSP for displaying items
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String addToCart(@RequestParam("customerId") int customerId,
                            @RequestParam("inventoryId") int inventoryId) {
        userInfoService.addToCart(customerId, inventoryId);
        return "redirect:/customerDashboard"; // Redirect back to dashboard
    }
}
