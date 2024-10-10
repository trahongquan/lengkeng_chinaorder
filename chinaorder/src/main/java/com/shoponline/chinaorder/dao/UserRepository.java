package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
//    Users findByPeopleid(int id);
    void deleteByUsername(String username);
}

