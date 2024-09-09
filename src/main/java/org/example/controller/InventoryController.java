package org.example.controller;

import org.example.model.Inventory;
import org.example.model.InventoryCategory;
import org.example.service.InventoryCategoryService;
import org.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryCategoryService categoryService;

    @GetMapping("/add")
    public String showInventoryPage() {
        return "addInventory";
    }

    @PostMapping("/save")
    public String saveInventory(@RequestParam("categoryName") String categoryName,
                                @RequestParam("inventoryName") String inventoryName,
                                @RequestParam("quantity") int quantity,
                                @RequestParam("price") double price,
                                @RequestParam("image") MultipartFile image,
                                @RequestParam("description") String description,
                                Model model) {
        if (categoryName.isEmpty() || inventoryName.isEmpty() || quantity <= 0 || price <= 0 || image.isEmpty() || description.isEmpty()) {
            model.addAttribute("errorMessage", "Incomplete Inventory Data, please recheck!!");
            return "errorPage";
        }
        InventoryCategory category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new InventoryCategory();
            category.setCategoryName(categoryName);
            categoryService.saveCategory(category);
        }
        Inventory inventory = new Inventory();
        inventory.setInventoryName(inventoryName);
        inventory.setQuantity(quantity);
        inventory.setPrice(price);
        inventory.setImage(image.getOriginalFilename()); // Save image file path
        inventory.setDescription(description);
        inventory.setCategory(category);

        inventoryService.saveInventory(inventory);
        return "redirect:/inventory/list"; // Redirect to a page listing inventories
    }
}