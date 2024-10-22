// SizeService.java
package com.shoponline.chinaorder.service.attribute;

import com.shoponline.chinaorder.entity.Attribute;

import java.util.List;

public interface AttributeService {
    List<Attribute> getAllAttribute();

    Attribute createAttribute(Attribute size);

    Attribute findAttributeById(int id);

    void deleteAttribute(int id);

    List<Attribute> findAttributesNotUsedInAttributeValues();
}
