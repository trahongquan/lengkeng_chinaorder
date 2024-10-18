// DistrictService.java
package com.shoponline.chinaorder.service.district;

import com.shoponline.chinaorder.entity.District;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();

    District createDistrict(District district);

    District findDistrictById(int id);

    void deleteDistrict(int id);
}
