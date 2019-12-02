package otc.be.repository;

import org.springframework.data.repository.CrudRepository;
import otc.be.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Iterable<Booking> findByOrderByIdAsc();
}
