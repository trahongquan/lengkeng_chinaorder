// SupplierServiceImpl.java
package com.shoponline.chinaorder.service.supplier;

import com.shoponline.chinaorder.dao.SupplierRepository;
import com.shoponline.chinaorder.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Supplier> findSuppliersNotUsedInProducts() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Supplier> query = entityManager.createQuery(
                "SELECT s FROM Supplier s WHERE s NOT IN (SELECT p.supplier FROM Products p)", Supplier.class);
        return query.getResultList();
    }

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        if(supplier.getId()!=0){
            Supplier supplier_old = this.findSupplierById(supplier.getId());
            supplier_old.setSuppliername(supplier.getSuppliername());
            supplier_old.setAddress(supplier.getAddress());
            supplier_old.setContact(supplier.getContact());
            return supplierRepository.save(supplier_old);
        } else {
            return supplierRepository.save(supplier);
        }
    }

    @Override
    public Supplier findSupplierById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSupplier(int id) {
        supplierRepository.deleteById(id);
    }


}
