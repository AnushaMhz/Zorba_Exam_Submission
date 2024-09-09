package org.example.service;

import org.example.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class InventoryService {

    @PersistenceContext
    private EntityManager entityManager;

    public Inventory saveInventory(Inventory inventory) {
        if(inventory.getInventoryId()==0) {
            entityManager.persist(inventory);
            return inventory;
        }else  {
            return entityManager.merge(inventory);
        }
    }
}
