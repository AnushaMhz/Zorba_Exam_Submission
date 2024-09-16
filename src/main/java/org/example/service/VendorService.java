package org.example.service;

import org.example.controller.VendorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class VendorService {

    @Autowired
    private EntityManager entityManager;

    public List<VendorController> getAllVendor() {
        return entityManager.createQuery("FROM Vendor", VendorController.class).getResultList();

    }
    @Transactional
    public void deleteVendor(String vendor) {
        VendorController vendorController = entityManager.find(VendorController.class, vendor);
        if(vendor != null) {
            entityManager.remove(vendor);
        }
    }
}
