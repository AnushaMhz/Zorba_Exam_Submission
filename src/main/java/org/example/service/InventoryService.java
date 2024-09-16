package org.example.service;

import org.example.model.Inventory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

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
    public List<Inventory> getAllInventory() {
        Query query = entityManager.createQuery("FROM Inventory", Inventory.class);
        return query.getResultList();
    }
}
