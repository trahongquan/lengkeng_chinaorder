package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.AttributeValue;
import com.shoponline.chinaorder.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
    List<AttributeValue> findAllByProduct(Products products);
}
