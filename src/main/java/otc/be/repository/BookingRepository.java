package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import otc.be.entity.Booking;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.LinkedList;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Iterable<Booking> findByOrderByIdAsc();

    @Query(value = "SELECT * FROM booking where id_restaurant_table LIKE %:restTable%", nativeQuery = true)
    LinkedList<Booking> findRestTableByOrderByIdAsc(@Param("restTable") Integer restTable);

    @Query(value = "SELECT * FROM booking where id_user=?1 AND (local_date_time > ?2)", nativeQuery = true)
    LinkedList<Booking> getListAllBookingsinFuture(@Param("id") Integer id, @Param("localDateTime") LocalDateTime localDateTime);

    @Query(value = "SELECT * FROM booking where id_restaurant=?1 AND ((date > ?2))", nativeQuery = true)
    LinkedList<Booking> getFutureBookingsByRestaurant(@Param("id") Integer id, @Param("datum") Date datum);
}