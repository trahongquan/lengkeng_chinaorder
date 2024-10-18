package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Integer> {

}
