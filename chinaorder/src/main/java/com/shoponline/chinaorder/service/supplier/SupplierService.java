// SupplierService.java
package com.shoponline.chinaorder.service.supplier;

import com.shoponline.chinaorder.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier createSupplier(Supplier supplier);

    Supplier findSupplierById(int id);

    void deleteSupplier(int id);

    List<Supplier> findSuppliersNotUsedInProducts();
}
