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
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select an Excel file to upload.");
            return "uploadExcel";
        }

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String categoryName = row.getCell(0).getStringCellValue();
                String itemName = row.getCell(1).getStringCellValue();
                int quantity = (int) row.getCell(2).getNumericCellValue();
                double price = row.getCell(3).getNumericCellValue();
                String description = row.getCell(4).getStringCellValue();

                inventoryService.saveInventoryData(categoryName, itemName, quantity, price, description);
            }

            model.addAttribute("message", "Excel file uploaded and data saved successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
        }

        return "uploadExcel";
    }

    @RequestMapping(value = "/viewInventory", method = RequestMethod.GET)
    public String viewInventory(Model model) {
        List<Inventory> inventoryList = inventoryService.findAll();
        model.addAttribute("inventoryList", inventoryList);
        return "viewInventory";
    }
}