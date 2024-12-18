package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
        List<Image> findAllByImgurlContains(String imgUrl);
}
