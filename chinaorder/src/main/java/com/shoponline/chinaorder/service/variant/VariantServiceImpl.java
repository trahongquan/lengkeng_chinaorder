// VariantServiceImpl.java
package com.shoponline.chinaorder.service.variant;

import com.shoponline.chinaorder.dao.VariantRepository;
import com.shoponline.chinaorder.entity.Variants;
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
        return variantRepository.save(variant);
    }

    @Override
    public Variants findVariantById(int id) {
        return variantRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVariant(int id) {
        variantRepository.deleteById(id);
    }
}
