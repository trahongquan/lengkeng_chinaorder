// ReviewServiceImpl.java
package com.shoponline.chinaorder.service.review;

import com.shoponline.chinaorder.dao.ReviewRepository;
import com.shoponline.chinaorder.entity.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Reviews createReview(Reviews review) {
        return reviewRepository.save(review);
    }

    @Override
    public Reviews findReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
