package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.RestaurantTable;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantTableRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public boolean getRestaurantTableByIdIsFree(Integer id, LocalDateTime ldtAnfrage) {
        Optional<RestaurantTable> oRT = getRestaurantTableById(id);
        boolean reservierenIstMoeglich = true;
        if (oRT.isPresent()) {
            RestaurantTable currentRT = oRT.get();
            LocalDateTime ldt1HourBefore = ldtAnfrage.minusMinutes(59);
            LocalDateTime ldt1HourLater = ldtAnfrage.plusMinutes(59);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            LinkedList<Booking> bookings = bookingRepository.findRestTableByOrderByIdAsc(id);

            if (bookings.size() != 0) {
                for (int i = 0; i < bookings.size(); i++) {
                    LocalDateTime localDateTime = bookings.get(i).getLocalDateTime();

                    if (localDateTime.isBefore(ldt1HourBefore)) {
                        System.out.println("Das Buchungsdatum liegt vor dem angefragten Zeitraum (ldt1HourBefore), Buchung wäre möglich");
                    } else {
                        if (localDateTime.isAfter(ldt1HourLater)) {
                            System.out.println("Das Buchungsdatum liegt nach dem angefragten Zeitraum (ldt1HourLater), Buchung wäre möglich");
                        } else {
                            System.out.println("Das Buchungsdatum liegt im angefragten Zeitraum, Buchung ist nicht möglich!");
                            reservierenIstMoeglich = false;
                            i = bookings.size();
                        }
                    }
                }
            } else {
                System.out.println("Es gibt keine Einträge in Bookings - alle Termine sind frei verfügbar. ");
            }
        } else {
            System.out.println("Es gibt keinen Tisch mit der ID " + id + ".");
            reservierenIstMoeglich = false;
        }
        return reservierenIstMoeglich;
    }
}