package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Restaurant;
import otc.be.entity.Review;
import otc.be.repository.RestaurantRepository;
import otc.be.repository.ReviewRepository;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantController restaurantController;

    public Iterable<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Iterable<Review> getReviewsByRestaurant(int restId){
        return reviewRepository.findReviewsByRestaurant(restId);
    }

    public Review create(Review review){
        reviewRepository.save(review);
        Restaurant ratedRest = restaurantRepository.findById(review.getId().getRestaurantId()).get();
        ratedRest.setAvgReview(calcAvgReview(ratedRest.getId()));
        restaurantController.update(ratedRest);
        return review;
    }

    public double calcAvgReview(int restId){
        Iterable<Review> reviews = reviewRepository.findReviewsByRestaurant(restId);
        List<Integer> ratings = new LinkedList<>();

        reviews.forEach(rev -> ratings.add(rev.getRating()));
        double sum = 0;
        for (int rate : ratings) {
            sum += rate;
        }

        return sum / ratings.size();
    }
}
