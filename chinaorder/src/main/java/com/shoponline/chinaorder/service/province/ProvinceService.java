// ProvinceService.java
package com.shoponline.chinaorder.service.province;

import com.shoponline.chinaorder.entity.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> getAllProvinces();

    Province createProvince(Province province);

    Province findProvinceById(int id);

    void deleteProvince(int id);
}
