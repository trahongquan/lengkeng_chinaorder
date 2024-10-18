// CategoryService.java
package com.shoponline.chinaorder.service.category;

import com.shoponline.chinaorder.entity.Categories;

import java.util.List;

public interface CategoryService {
    List<Categories> getAllCategories();

    Categories createCategory(Categories category);

    Categories findCategoryById(int id);

    void deleteCategory(int id);

    List<Categories> findCategoriesNotUsedInProducts();

}
