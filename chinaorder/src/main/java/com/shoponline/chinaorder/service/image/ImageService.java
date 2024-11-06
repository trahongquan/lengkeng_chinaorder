// ImageService.java
package com.shoponline.chinaorder.service.image;

import com.shoponline.chinaorder.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> getAllImages();

    Image createImage(Image image);

    Image findImageById(int id);

    void deleteImage(int id);

    List<Image> findImagesByUrl(String imgUrl);
}
