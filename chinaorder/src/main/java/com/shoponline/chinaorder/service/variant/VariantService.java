// VariantService.java
package com.shoponline.chinaorder.service.variant;

import com.shoponline.chinaorder.entity.Variants;

import java.util.List;

public interface VariantService {
    List<Variants> getAllVariants();

    Variants createVariant(Variants variant);

    Variants findVariantById(int id);

    void deleteVariant(int id);
}
