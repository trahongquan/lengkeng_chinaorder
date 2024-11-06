// ImageServiceImpl.java
package com.shoponline.chinaorder.service.imageProduct;

import com.shoponline.chinaorder.dao.ImageProductRepository;
import com.shoponline.chinaorder.dao.ImageRepository;
import com.shoponline.chinaorder.entity.ImageProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProductServiceImpl implements ImageProductService {

    @Autowired
    private ImageProductRepository imageProductRepository;

    @Override
    public List<ImageProduct> getAllImageProducts() {
        return imageProductRepository.findAll();
    }

    @Override
    public ImageProduct createImageProduct(ImageProduct imageProduct) {
        return imageProductRepository.save(imageProduct);
    }

    @Override
    public ImageProduct findImageProductById(int id) {
        return imageProductRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImageProduct(int id) {
        imageProductRepository.deleteById(id);
    }

    @Override
    public List<ImageProduct> findImagesByUrl(String imgUrl) {
        return imageProductRepository.findAllByImgurlContains(imgUrl);
    }
}
