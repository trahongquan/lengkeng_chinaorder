// ColorService.java
package com.shoponline.chinaorder.service.color;

import com.shoponline.chinaorder.entity.Colors;

import java.util.List;

public interface ColorService {
    List<Colors> getAllColors();

    Colors createColor(Colors color);

    Colors findColorById(int id);

    void deleteColor(int id);
}
