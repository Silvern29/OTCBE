package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.Restaurant;
import otc.be.entity.RestaurantTable;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantTableRepository;

import javax.swing.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class RestaurantTableController {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    private BookingRepository bookingRepository;

    public Iterable<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findByOrderByIdAsc();
    }

    public Optional<RestaurantTable> getRestaurantTableById(Integer id) {
        return restaurantTableRepository.findById(id);
    }

    public void create(RestaurantTable restaurantTable) {
        restaurantTableRepository.save(restaurantTable);
        System.out.println("Jetzt sollte ein neuer Tisch in der Tabelle RestaurantTable eingetragen worden sein.");
    }

    public RestaurantTable update(RestaurantTable restaurantTable) {
        RestaurantTable updatedRestaurantTable = restaurantTableRepository.findById(restaurantTable.getId()).get();
        if (updatedRestaurantTable.getTableNrRest() > 0)
            updatedRestaurantTable.setTableNrRest(restaurantTable.getTableNrRest());
        if (updatedRestaurantTable.getPax() > 0) updatedRestaurantTable.setPax(restaurantTable.getPax());
        if (updatedRestaurantTable.getRestaurant().getId() != restaurantTable.getRestaurant().getId())
            updatedRestaurantTable.setRestaurant(restaurantTable.getRestaurant());
        restaurantTableRepository.save(updatedRestaurantTable);
        System.out.println("Der Tisch mit der ID " + updatedRestaurantTable.getId() + " ist auf Personenzahl: " + updatedRestaurantTable.getPax() + " Restaurant-ID" + updatedRestaurantTable.getRestaurant().getId() + " Rest.-Tisch-ID" + updatedRestaurantTable.getTableNrRest() + "geändert.");
        return updatedRestaurantTable;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der Tisch mit der ID " + id + " gelöscht worden sein.");
        restaurantTableRepository.deleteById(id);
    }

    public /*LinkedList<Date>*/ void getRestaurantTableByIdIsFree(Integer id) {
        Optional<RestaurantTable> oRT = getRestaurantTableById(id);
        if (oRT.isPresent()) {
            RestaurantTable currentRT = oRT.get();
            System.out.println("Der Tisch Nr. " + id + " gehört zum Restaurant ID " + currentRT.getRestaurant().getId() + ", Name " + currentRT.getRestaurant().getName() + ", dessen Tisch-Nr. ist " + currentRT.getTableNrRest() + ", Platz für " + currentRT.getPax() + " Personen.");
            LinkedList<Booking> bookings;
            bookings = bookingRepository.findRestTableByOrderByIdAsc(id);
            if (bookings.size() != 0) {
                for (int i = 0; i < bookings.size(); i++) {
                    System.out.println("gebucht .. rest morgen");
                }
            } else {
                System.out.println("Es gibt keine Einträge in Bookings - alle Termine sind frei verfügbar. ");

            }
        } else {
            System.out.println("Es gibt keinen Tisch mit der ID " + id + ".");
        }
    }
}