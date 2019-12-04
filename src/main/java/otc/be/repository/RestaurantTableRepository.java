package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import otc.be.entity.RestaurantTable;

import java.util.LinkedList;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {

    Iterable<RestaurantTable> findByOrderByIdAsc();
    //zum Ausprobieren und Herausfinden aufgehoben
    //@Query(value = "SELECT * FROM restaurant_table where id_restaurant LIKE %:id_rest% AND pax LIKE %:pax1%", nativeQuery = true)
    @Query(value = "SELECT * FROM restaurant_table where id_restaurant =?1 AND pax >= ?2 ORDER BY pax, table_nr_rest ", nativeQuery = true)
    LinkedList<RestaurantTable> getListTablesPAX(@Param("id_rest") int id_rest, @Param("pax1") int pax1);
}