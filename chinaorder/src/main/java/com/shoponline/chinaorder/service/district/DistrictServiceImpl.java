// DistrictServiceImpl.java
package com.shoponline.chinaorder.service.district;

import com.shoponline.chinaorder.dao.DistrictRepository;
import com.shoponline.chinaorder.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District findDistrictById(int id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDistrict(int id) {
        districtRepository.deleteById(id);
    }
}
