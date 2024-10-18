// BrandService.java
package com.shoponline.chinaorder.service.brand;

import com.shoponline.chinaorder.entity.Brands;

import java.util.List;

public interface BrandService {
    List<Brands> getAllBrands();

    Brands createBrand(Brands brand);

    Brands findBrandById(int id);

    void deleteBrand(int id);

    List<Brands> findBrandsNotUsedInProducts();

}
