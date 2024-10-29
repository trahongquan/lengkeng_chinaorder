// StatusServiceImpl.java
package com.shoponline.chinaorder.service.status;

import com.shoponline.chinaorder.dao.StatusRepository;
import com.shoponline.chinaorder.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Status> findStatusNotUsedInOrder() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Status> query = entityManager.createQuery(
                "SELECT s FROM Status s WHERE s NOT IN (SELECT o.status FROM Order o)", Status.class);
        return query.getResultList();
    }
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status findStatusById(int id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStatus(int id) {
        statusRepository.deleteById(id);
    }
}
