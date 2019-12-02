package otc.be.repository;

import org.springframework.data.repository.CrudRepository;
import otc.be.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Optional<Restaurant> findById(int id);
    Iterable<Restaurant> findByOrderByIdAsc();

}
