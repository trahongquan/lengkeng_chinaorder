// ProductServiceImpl.java
package com.shoponline.chinaorder.service.product;

import com.shoponline.chinaorder.dao.ProductRepository;
import com.shoponline.chinaorder.entity.Products;
import com.shoponline.chinaorder.entity.Variants;
import com.shoponline.chinaorder.service.variant.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products createProduct(Products product) {
        if(product.getId()!=0){
            Products products2 = this.findProductById(product.getId());
            products2.setProductName(product.getProductName());
            products2.setCategory(product.getCategory());
            products2.setBrand(product.getBrand());
            products2.setSupplier(product.getSupplier());
            products2.setUnit(product.getUnit());
            products2.setProductDesc(product.getProductDesc());
            return productRepository.save(products2);
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public Products findProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(int id, VariantService variantService) {
        Products product = this.findProductById(id);
        product.setIsDelete(1);
        productRepository.save(product);
        List<Variants> variants = variantService.FindAllByProduct(product);
        variants.forEach(v -> {
            v.setIsDelete(1);
            variantService.createVariant(v);
        });
    }
}
