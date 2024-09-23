package org.example.dao;


import org.example.model.Customer;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CustomerDAO {
    List<Customer> findAll();

    void deleteById(int id);
}
