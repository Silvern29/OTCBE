package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import otc.be.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Optional<Restaurant> findById(int id);

    Iterable<Restaurant> findByOrderByIdAsc();

    @Query(value = "SELECT * FROM restaurant where name LIKE %:fragment% OR city LIKE %:fragment% ORDER BY name, id", nativeQuery = true)
    Iterable<Restaurant> findAllWithFragment(@Param("fragment") String fragment);

}