package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.ImageBanner;
import com.shoponline.chinaorder.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageBannerRepository extends JpaRepository<ImageBanner, Integer> {
        List<ImageBanner> findAllByImgurlContains(String imgUrl);
}
