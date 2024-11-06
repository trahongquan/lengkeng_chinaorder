// ImageService.java
package com.shoponline.chinaorder.service.imageBannerr.imageProduct;

import com.shoponline.chinaorder.entity.ImageBanner;
import com.shoponline.chinaorder.entity.ImageProduct;

import java.util.List;

public interface ImageBannerService {
    List<ImageBanner> getAllImageBanners();

    List<ImageBanner> getAllImageBannersActive();

    ImageBanner createImageBanner(ImageBanner image);

    ImageBanner findImageBannerById(int id);

    void deleteImageBanner(int id);

    List<ImageBanner> findImagesByUrl(String imgUrl);
}
