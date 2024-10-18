// UnitService.java
package com.shoponline.chinaorder.service.unit;

import com.shoponline.chinaorder.entity.Unit;

import java.util.List;

public interface UnitService {
    List<Unit> getAllUnits();

    Unit createUnit(Unit unit);

    Unit findUnitById(int id);

    void deleteUnit(int id);

    List<Unit> findUnitsNotUsedInProducts();

}
