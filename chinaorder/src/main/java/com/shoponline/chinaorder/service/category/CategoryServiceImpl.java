// CategoryServiceImpl.java
package com.shoponline.chinaorder.service.category;

import com.shoponline.chinaorder.dao.CategoryRepository;
import com.shoponline.chinaorder.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Categories> findCategoriesNotUsedInProducts() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Categories> query = entityManager.createQuery(
                "SELECT c FROM Categories c WHERE c NOT IN (SELECT p.category FROM Products p)", Categories.class);
        return query.getResultList();
    }
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories createCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories findCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
