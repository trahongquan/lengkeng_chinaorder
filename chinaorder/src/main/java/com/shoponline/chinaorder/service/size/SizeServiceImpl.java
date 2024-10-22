// SizeServiceImpl.java
package com.shoponline.chinaorder.service.size;

import com.shoponline.chinaorder.dao.SizeRepository;
import com.shoponline.chinaorder.entity.Sizes;
import com.shoponline.chinaorder.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Sizes> findSizesNotUsedInVariants() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Sizes> query = entityManager.createQuery(
                "SELECT s FROM Sizes s WHERE s NOT IN (SELECT v.size FROM Variants v)", Sizes.class);
        return query.getResultList();
    }

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Sizes> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Sizes createSize(Sizes size) {
        return sizeRepository.save(size);
    }

    @Override
    public Sizes findSizeById(int id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSize(int id) {
        sizeRepository.deleteById(id);
    }
}
