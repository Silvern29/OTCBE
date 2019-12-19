package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.ReviewController;
import otc.be.entity.Review;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class ReviewAPI {
    @Autowired
    private ReviewController reviewController;

    @RequestMapping(method = RequestMethod.GET, path = "/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Review> getAllReviews() {
        return reviewController.getAllReviews();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public Review create(@RequestBody Review review) {
        return reviewController.create(review);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/reviews/{restaurant_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Review> getReviewsByRestaurant(@PathVariable(name = "restaurant_id") int restaurantId) {
        return reviewController.getReviewsByRestaurant(restaurantId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/reviews/avg/{restaurant_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getAvgReviewByRestaurant(@PathVariable(name = "restaurant_id") int restaurantId) {
        return reviewController.calcAvgReview(restaurantId);
    }



}
