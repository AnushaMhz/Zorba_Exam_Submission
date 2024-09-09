package org.example.service;

import org.example.model.InventoryCategory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class InventoryCategoryService {

    @PersistenceContext
    private EntityManager entityManager;

    public InventoryCategory saveCategory(InventoryCategory category) {
        if(category.getCategoryId()==0) {
            entityManager.persist(category);
            return category;
        }else {
            return entityManager.merge(category);
        }
    }
    public InventoryCategory getCategoryByName(String categoryName) {
        try {
            return entityManager.createQuery("SELECT c FROM InventoryCategory c WHERE c.categoryName = :categoryName", InventoryCategory.class)
                    .setParameter("categoryName", categoryName)
                    .getSingleResult();

        }catch (Exception e) {
            return null;
        }
    }

}
