package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Sizes, Integer> {

}
