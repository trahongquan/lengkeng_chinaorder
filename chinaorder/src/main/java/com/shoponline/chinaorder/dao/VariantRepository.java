package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variants, Integer> {

}
