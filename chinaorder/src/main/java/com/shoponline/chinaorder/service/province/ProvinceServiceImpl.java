// ProvinceServiceImpl.java
package com.shoponline.chinaorder.service.province;

import com.shoponline.chinaorder.dao.ProvinceRepository;
import com.shoponline.chinaorder.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Province createProvince(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Province findProvinceById(int id) {
        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProvince(int id) {
        provinceRepository.deleteById(id);
    }
}
