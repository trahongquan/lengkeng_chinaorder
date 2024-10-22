// SizeServiceImpl.java
package com.shoponline.chinaorder.service.attributevalue.attribute;

import com.shoponline.chinaorder.dao.AttributeRepository;
import com.shoponline.chinaorder.dao.AttributeValueRepository;
import com.shoponline.chinaorder.entity.Attribute;
import com.shoponline.chinaorder.entity.AttributeValue;
import com.shoponline.chinaorder.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Override
    public List<AttributeValue> getAllAttributeValue() {
        return attributeValueRepository.findAll();
    }

    @Override
    public AttributeValue createAttributeValue(AttributeValue attributeValue) {
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public AttributeValue findAttributeValueById(int id) {
        return attributeValueRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttributeValue(int id) {
        attributeValueRepository.deleteById(id);
    }

    @Override
    public List<AttributeValue> findAllByProduct(Products products){
        return attributeValueRepository.findAllByProduct(products);
    }
}
