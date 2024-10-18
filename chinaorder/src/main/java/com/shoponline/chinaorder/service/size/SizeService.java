// SizeService.java
package com.shoponline.chinaorder.service.size;

import com.shoponline.chinaorder.entity.Sizes;

import java.util.List;

public interface SizeService {
    List<Sizes> getAllSizes();

    Sizes createSize(Sizes size);

    Sizes findSizeById(int id);

    void deleteSize(int id);
}
