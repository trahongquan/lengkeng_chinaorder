package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Image;
import com.shoponline.chinaorder.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
        List<ImageProduct> findAllByImgurlContains(String imgUrl);
}
