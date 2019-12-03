package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.Restaurant;
import otc.be.entity.RestaurantTable;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantRepository;
import otc.be.repository.RestaurantTableRepository;

import java.util.Collections;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;


    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findByOrderByIdAsc();
    }

    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public void create(Booking booking) {
        //Kontrolle, dass das mitgegebene Restaurant auch tatsächlich dem gebuchten Tisch entspricht
        Optional<RestaurantTable> tempRT = restaurantTableRepository.findById(booking.getRestaurantTable().getId());
        if (tempRT.isPresent()) {
            //lies daraus das eingetragene Restaurant aus
            Restaurant tempR = tempRT.get().getRestaurant();
            //schreibe das zur eingegebenen TischID zugehörige Restaurant im nächsten Schritt in die DB
            booking.setRestaurant(tempR);
        }
        bookingRepository.save(booking);
        System.out.println("Jetzt sollte eine neue Buchung in der Tabelle Booking eingetragen worden sein.");
    }

    public Booking update(Booking booking) {
        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();

        //ist ein Datum eingegeben? -> weicht das in der DB hinterlegte Buchungsdatum vom eingegebenen Buchungsdatum ab, dann schreibe das eingegebene Datum im nächsten Schritt in die DB
        if ((booking.getDate() != null) && (booking.getDate() != updatedBooking.getDate()))
            updatedBooking.setDate(booking.getDate());
        //ist eine Uhrzeit eingegeben? -> weicht die in der DB hinterlegte Uhrzeit von der eingegebenen Uhrzeit ab, dann schreibe die eingegebene Uhrzeit im nächsten Schritt in die DB
        if ((booking.getTime() != null) && (booking.getTime() != updatedBooking.getTime()))
            updatedBooking.setTime(booking.getTime());
        //ist ein User eingegeben? -> weicht der in der DB hinterlegte User vom eingegebenen User ab, dann schreibe den eingegebenen User im nächsten Schritt in die DB
        if ((booking.getUser().getId() != 0) && (booking.getUser().getId() != updatedBooking.getUser().getId()))
            updatedBooking.setUser(booking.getUser());
        //ist ein Tisch eingegeben? -> weicht der in der DB hinterlegte Tisch vom eingegebenen Tisch ab, dann schreibe den eingegebenen Tisch im nächsten Schritt in die DB
        if ((booking.getRestaurantTable().getId() != 0) && (updatedBooking.getRestaurantTable().getId() != booking.getRestaurantTable().getId())) {
            updatedBooking.setRestaurantTable(booking.getRestaurantTable());
            //lies aus der DB den Eintrag für den eingegebenen, mit der ID erwähnten Tisch aus
            Optional<RestaurantTable> tempRT = restaurantTableRepository.findById(booking.getRestaurantTable().getId());
            if (tempRT.isPresent()) {
                //lies daraus das eingetragene Restaurant aus
                Restaurant tempR = tempRT.get().getRestaurant();
                //schreibe das zur eingegebenen TischID zugehörige Restaurant im nächsten Schritt in die DB
                updatedBooking.setRestaurant(tempR);
            }
        }
        //ist ein Restaurant eingegeben - würde ich weglassen, da beim Eintrag des Restaurants ohnehin geschaut wird, dass das laut Tisch-ID hinterlegte Restaurant in der DB gespeichert wird. Umsetzung bei Create fehlt noch!!!
        //Schreiben in die DB
        bookingRepository.save(updatedBooking);
        System.out.println("Die Änderung der Buchung sollte erfasst sein.");
        return updatedBooking;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte das Restaurant mit der ID " + id + " gelöscht worden sein.");
        bookingRepository.deleteById(id);
    }
}