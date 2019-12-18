package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Review;
import otc.be.repository.ReviewRepository;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    public Iterable<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Iterable<Review> getReviewsByRestaurant(int restId){
        return reviewRepository.findReviewsByRestaurant(restId);
    }

    public void create(Review review){
        reviewRepository.save(review);
    }

    public double getAvgReview(int restId){
        Iterable<Review> reviews = reviewRepository.findReviewsByRestaurant(restId);
        List<Integer> ratings = new LinkedList<>();

        reviews.forEach(rev -> ratings.add(rev.getRating()));
        int sum = 0;
        for (int rate : ratings) {
            sum += rate;
        }
        return sum /= ratings.size();
    }
}
