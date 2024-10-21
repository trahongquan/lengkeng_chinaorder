// ProductService.java
package com.shoponline.chinaorder.service.product;

import com.shoponline.chinaorder.entity.Products;
import com.shoponline.chinaorder.service.variant.VariantService;

import java.util.List;

public interface ProductService {
    List<Products> getAllProducts();

    Products createProduct(Products product);

    Products findProductById(int id);

    void deleteProduct(int id, VariantService variantService);
}
