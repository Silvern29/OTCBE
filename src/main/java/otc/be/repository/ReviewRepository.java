package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otc.be.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review WHERE restaurant_id=?1", nativeQuery = true)
    Iterable<Review> findReviewsByRestaurant(@Param("restaurant_id") int restaurant_id);
}
