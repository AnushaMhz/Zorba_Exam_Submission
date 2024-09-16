package org.example.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        inventory.setCategory(String.valueOf(category));

        inventoryService.saveInventory(inventory);
        return "redirect:/inventory/list"; // Redirect to a page listing inventories
    }

    @GetMapping("/upload")
    public String uploadExcel() {
        return "upload"; //jsp page to upload excel
    }

   @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request, Model model) {
        try {
            InputStream inputStream = request.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();

            List<Inventory> inventoryList = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Inventory inventory = new Inventory();
                inventory.setCategory(row.getCell(0).getStringCellValue());
                inventory.setInventoryName(row.getCell(1).getStringCellValue());
                inventory.setQuantity((int)row.getCell(2).getNumericCellValue());
                inventory.setPrice(row.getCell(3).getNumericCellValue());
                inventory.setDescription(row.getCell(4).getStringCellValue());

                inventory.setImageUrl("");
                inventoryList.add(inventory);
            }
            workbook.close();
            inputStream.close();

            model.addAttribute("inventoryList", inventoryList);
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Error!!");
            return "error.jsp";

        }
        return "inventoryList";
   }
}