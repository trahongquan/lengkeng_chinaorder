// SizeService.java
package com.shoponline.chinaorder.service.attributevalue.attribute;

import com.shoponline.chinaorder.entity.Attribute;
import com.shoponline.chinaorder.entity.AttributeValue;
import com.shoponline.chinaorder.entity.Products;

import java.util.List;

public interface AttributeValueService {
    List<AttributeValue> getAllAttributeValue();

    AttributeValue createAttributeValue(AttributeValue attributeValue);

    AttributeValue findAttributeValueById(int id);

    void deleteAttributeValue(int id);

    List<AttributeValue> findAllByProduct(Products products);
}
