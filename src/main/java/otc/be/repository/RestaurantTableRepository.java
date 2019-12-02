package otc.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import otc.be.entity.RestaurantTable;

import java.util.List;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {
    Iterable<RestaurantTable> findByOrderByIdAsc();
}
