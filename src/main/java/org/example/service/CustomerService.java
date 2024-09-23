package org.example.service;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDao;

    // Method to get all customers
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    // Method to remove a customer by ID
    public void removeCustomer(int id) {
        customerDao.deleteById(id);
    }
}
