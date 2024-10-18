package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Colors, Integer> {

}
