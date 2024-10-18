// ColorServiceImpl.java
package com.shoponline.chinaorder.service.color;

import com.shoponline.chinaorder.dao.ColorRepository;
import com.shoponline.chinaorder.entity.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Colors> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Colors createColor(Colors color) {
        return colorRepository.save(color);
    }

    @Override
    public Colors findColorById(int id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteColor(int id) {
        colorRepository.deleteById(id);
    }
}
