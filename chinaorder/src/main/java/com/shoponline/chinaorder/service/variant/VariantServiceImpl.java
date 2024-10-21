// VariantServiceImpl.java
package com.shoponline.chinaorder.service.variant;

import com.shoponline.chinaorder.dao.VariantRepository;
import com.shoponline.chinaorder.entity.Products;
import com.shoponline.chinaorder.entity.Variants;
import com.shoponline.chinaorder.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantRepository variantRepository;

    @Override
    public List<Variants> getAllVariants() {
        return variantRepository.findAll();
    }

    @Override
    public Variants createVariant(Variants variant) {
        if(variant.getId()!=0){
            Variants variant2 = this.findVariantById(variant.getId());
            variant2.setProduct(variant.getProduct());
            variant2.setSku(variant.getSku());
            variant2.setStockQuantity(variant.getStockQuantity());
            variant2.setSize(variant.getSize());
            variant2.setColor(variant.getColor());
            variant2.setCostPrice(variant.getCostPrice());
            variant2.setSellingPrice(variant.getSellingPrice());
            return variantRepository.save(variant2);
        } else {
            return variantRepository.save(variant);
        }
    }

    @Override
    public Variants findVariantById(int id) {
        return variantRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVariant(int id) {
        variantRepository.deleteById(id);
    }

    @Override
    public List<Variants> FindAllByProduct(Products products){
        return variantRepository.findAllByProduct(products);
    }
}
