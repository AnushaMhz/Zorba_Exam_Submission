package org.example.service;

import org.example.model.Inventory;
import org.example.model.InventoryCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InventoryService {

    private SessionFactory sessionFactory;

    @Autowired
    public InventoryService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveInventoryData(String categoryName, String itemName, int quantity, double price, String description) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            // Find or create the inventory category
            InventoryCategory category = session.createQuery("FROM InventoryCategory WHERE categoryName = :name", InventoryCategory.class)
                    .setParameter("name", categoryName)
                    .uniqueResult();

            if (category == null) {
                category = new InventoryCategory();
                category.setCategoryName(categoryName);
                session.save(category);
            }

            // Create and save inventory item
            Inventory inventory = new Inventory();
            inventory.setInventoryName(itemName);
            inventory.setQuantity(quantity);
            inventory.setPrice(price);
            inventory.setDescription(description);
            inventory.setCategory(categoryName);

            session.save(inventory);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Inventory> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Inventory> inventoryList = null;

        try {
            inventoryList = session.createQuery("FROM Inventory", Inventory.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return inventoryList;
    }
}