// ImageService.java
package com.shoponline.chinaorder.service.imageProduct;

import com.shoponline.chinaorder.entity.Image;
import com.shoponline.chinaorder.entity.ImageProduct;

import java.util.List;

public interface ImageProductService {
    List<ImageProduct> getAllImageProducts();

    ImageProduct createImageProduct(ImageProduct image);

    ImageProduct findImageProductById(int id);

    void deleteImageProduct(int id);

    List<ImageProduct> findImagesByUrl(String imgUrl);
}
