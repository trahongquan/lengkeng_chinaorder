// SizeServiceImpl.java
package com.shoponline.chinaorder.service.size;

import com.shoponline.chinaorder.dao.SizeRepository;
import com.shoponline.chinaorder.entity.Sizes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

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
