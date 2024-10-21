package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Products;
import com.shoponline.chinaorder.entity.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variants, Integer> {
    List<Variants> findAllByProduct(Products products);
}
