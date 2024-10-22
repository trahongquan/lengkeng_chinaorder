// BrandsServiceImpl.java
package com.shoponline.chinaorder.service.brand;

import com.shoponline.chinaorder.dao.BrandRepository;
import com.shoponline.chinaorder.entity.Brands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brands> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brands createBrand(Brands brand) {
        if(brand.getId() != 0){
            Brands brand_old = this.findBrandById(brand.getId());
            brand_old.setBrandname(brand.getBrandname());
            return brandRepository.save(brand_old);
        } else {
            return brandRepository.save(brand);
        }
    }

    @Override
    public Brands findBrandById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<Brands> findBrandsNotUsedInProducts() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Brands> query = entityManager.createQuery(
                "SELECT b FROM Brands b WHERE b NOT IN (SELECT p.brand FROM Products p)", Brands.class);
        return query.getResultList();
    }
}
