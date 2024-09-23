package org.example.service;

import org.example.model.Customer;
import org.example.model.Inventory;
import org.example.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public boolean validateAdmin(String username, String password, String role) {

        return "admin".equals(username) && "admin123".equals(password) && "Admin".equals(role);
    }

    public List<Customer> getAllCustomers() {

        return null;
    }


    public List<Vendor> getAllVendors() {
return null;
    }

    public List<Inventory> getAllInventory() {
        return null;
    }
}
