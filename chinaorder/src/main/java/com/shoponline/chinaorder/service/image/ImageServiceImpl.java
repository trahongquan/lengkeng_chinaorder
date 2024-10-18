// ImageServiceImpl.java
package com.shoponline.chinaorder.service.image;

import com.shoponline.chinaorder.dao.ImageRepository;
import com.shoponline.chinaorder.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image createImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image findImageById(int id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImage(int id) {
        imageRepository.deleteById(id);
    }
}
