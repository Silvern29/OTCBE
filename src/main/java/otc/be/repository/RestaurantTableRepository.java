package otc.be.repository;

import org.springframework.data.repository.CrudRepository;
import otc.be.entity.RestaurantTable;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {
    Iterable<RestaurantTable> findByOrderByIdAsc();

}