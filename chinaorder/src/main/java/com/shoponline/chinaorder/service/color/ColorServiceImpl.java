// ColorServiceImpl.java
package com.shoponline.chinaorder.service.color;

import com.shoponline.chinaorder.dao.ColorRepository;
import com.shoponline.chinaorder.entity.Attribute;
import com.shoponline.chinaorder.entity.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Colors> findColorsNotUsedInVariants() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Colors> query = entityManager.createQuery(
                "SELECT c FROM Colors c WHERE c NOT IN (SELECT v.color FROM Variants v)", Colors.class);
        return query.getResultList();
    }
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Colors> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Colors createColor(Colors color) {
        if(color.getId() != 0){
            Colors color_old = this.findColorById(color.getId());
            color_old.setColor(color.getColor());
            color_old.setHexCode(color.getHexCode());
            color_old.setAbbreviations(color.getAbbreviations());
            return colorRepository.save(color_old);
        } else {
            return colorRepository.save(color);
        }
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
