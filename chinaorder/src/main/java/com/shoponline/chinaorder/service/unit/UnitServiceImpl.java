// UnitServiceImpl.java
package com.shoponline.chinaorder.service.unit;

import com.shoponline.chinaorder.dao.UnitRepository;
import com.shoponline.chinaorder.entity.Categories;
import com.shoponline.chinaorder.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Unit> findUnitsNotUsedInProducts() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Unit> query = entityManager.createQuery(
                "SELECT c FROM Unit c WHERE c NOT IN (SELECT p.unit FROM Products p)", Unit.class);
        return query.getResultList();
    }
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @Override
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public Unit findUnitById(int id) {
        return unitRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUnit(int id) {
        unitRepository.deleteById(id);
    }
}
