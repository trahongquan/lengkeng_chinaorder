// ImageServiceImpl.java
package com.shoponline.chinaorder.service.imageBannerr.imageProduct;

import com.shoponline.chinaorder.dao.ImageBannerRepository;
import com.shoponline.chinaorder.entity.ImageBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageBannerServiceImpl implements ImageBannerService {

    @Autowired
    private ImageBannerRepository imageBannerRepository;

    @Override
    public List<ImageBanner> getAllImageBanners() {
        return imageBannerRepository.findAll();
    }

    @Override
    public List<ImageBanner> getAllImageBannersActive() {
        return imageBannerRepository.findAll().stream().filter(i -> i.getActive()==1).collect(Collectors.toList());
    }

    @Override
    public ImageBanner createImageBanner(ImageBanner imageBanner) {
        if(imageBanner.getId() != 0){
            return imageBannerRepository.save(new ImageBanner(
                    imageBanner.getType(),
                    imageBanner.getImgurl(),
                    imageBanner.getTitle(),
                    imageBanner.getSubtitle(),
                    imageBanner.getButtonText(),
                    imageBanner.getActive()
            ));
        } else {
            return imageBannerRepository.save(imageBanner);
        }
    }

    @Override
    public ImageBanner findImageBannerById(int id) {
        return imageBannerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImageBanner(int id) {
        imageBannerRepository.deleteById(id);
    }

    @Override
    public List<ImageBanner> findImagesByUrl(String imgUrl) {
        return imageBannerRepository.findAllByImgurlContains(imgUrl);
    }
}
