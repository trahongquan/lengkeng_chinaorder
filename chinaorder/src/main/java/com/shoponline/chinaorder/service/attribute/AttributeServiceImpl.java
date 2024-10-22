// SizeServiceImpl.java
package com.shoponline.chinaorder.service.attribute;

import com.shoponline.chinaorder.dao.AttributeRepository;
import com.shoponline.chinaorder.entity.Attribute;
import com.shoponline.chinaorder.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Attribute> findAttributesNotUsedInAttributeValues() {
        // Sử dụng JPQL để xây dựng truy vấn
        TypedQuery<Attribute> query = entityManager.createQuery(
                "SELECT a FROM Attribute a WHERE a NOT IN (SELECT av.attribute FROM AttributeValue av)", Attribute.class);
        return query.getResultList();
    }
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public List<Attribute> getAllAttribute() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute createAttribute(Attribute attribute) {
        if(attribute.getId() != 0){
            Attribute attribute_old = this.findAttributeById(attribute.getId());
            attribute_old.setName(attribute.getName());
            return attributeRepository.save(attribute_old);
        } else {
            return attributeRepository.save(attribute);
        }
    }

    @Override
    public Attribute findAttributeById(int id) {
        return attributeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttribute(int id) {
        attributeRepository.deleteById(id);
    }
}
