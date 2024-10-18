package com.shoponline.chinaorder.dao;

import com.shoponline.chinaorder.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer> {

}
