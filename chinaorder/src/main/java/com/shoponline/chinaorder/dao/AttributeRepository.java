package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Attribute;
import com.shoponline.chinaorder.entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

}
