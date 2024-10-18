// ReviewService.java
package com.shoponline.chinaorder.service.review;

import com.shoponline.chinaorder.entity.Reviews;

import java.util.List;

public interface ReviewService {
    List<Reviews> getAllReviews();

    Reviews createReview(Reviews review);

    Reviews findReviewById(int id);

    void deleteReview(int id);
}
