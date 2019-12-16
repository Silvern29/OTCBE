package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.RestaurantTable;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantTableRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class RestaurantTableController {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Iterable<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findByOrderByIdAsc();
    }

    public Optional<RestaurantTable> getRestaurantTableById(Integer id) {
        return restaurantTableRepository.findById(id);
    }

    public void create(RestaurantTable restaurantTable) {
        restaurantTableRepository.save(restaurantTable);
    }

    public RestaurantTable update(RestaurantTable restaurantTable) {
        RestaurantTable updatedRestaurantTable = restaurantTableRepository.findById(restaurantTable.getId()).get();
        if (updatedRestaurantTable.getTableNrRest() > 0)
            updatedRestaurantTable.setTableNrRest(restaurantTable.getTableNrRest());
        if (updatedRestaurantTable.getPax() > 0) updatedRestaurantTable.setPax(restaurantTable.getPax());
        if (updatedRestaurantTable.getRestaurant().getId() != restaurantTable.getRestaurant().getId())
            updatedRestaurantTable.setRestaurant(restaurantTable.getRestaurant());
        restaurantTableRepository.save(updatedRestaurantTable);
        return updatedRestaurantTable;
    }

    public void deleteById(Integer id) {
        restaurantTableRepository.deleteById(id);
    }

    public boolean getRestaurantTableByIdIsFree(Integer id, LocalDateTime ldtAnfrage) {
        Optional<RestaurantTable> oRT = getRestaurantTableById(id);
        boolean reservierenIstMoeglich = true;
        if (oRT.isPresent()) {
            LocalDateTime ldt1HourBefore = ldtAnfrage.minusMinutes(59);
            LocalDateTime ldt1HourLater = ldtAnfrage.plusMinutes(59);

            LinkedList<Booking> bookings = bookingRepository.findRestTableByOrderByIdAsc(id);
            if (bookings.size() > 0) {
                for (int i = 0; i < bookings.size(); i++) {
                    LocalDateTime localDateTime = bookings.get(i).getLocalDateTime();
                    if (localDateTime.isAfter(ldt1HourBefore) && localDateTime.isBefore((ldt1HourLater))) {
                        reservierenIstMoeglich = false;
                        i = bookings.size();
                    }
                }
            }
        } else {
            reservierenIstMoeglich = false;
        }
        return reservierenIstMoeglich;
    }
}