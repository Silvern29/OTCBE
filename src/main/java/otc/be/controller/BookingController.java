package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.dto.BookingDTO;
import otc.be.entity.Booking;
import otc.be.entity.Restaurant;
import otc.be.entity.RestaurantTable;
import otc.be.entity.User;
import otc.be.exception.NotLoggedInException;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantRepository;
import otc.be.repository.RestaurantTableRepository;
import otc.be.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private AuthorizationController authorizationController;

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findByOrderByIdAsc();
    }

    public Iterable<Booking> getFutureBookingsByResId(int id) {
        Date datum = Date.valueOf(LocalDate.now());
        return bookingRepository.getFutureBookingsByRestaurant(id, datum);
    }

    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Booking create(BookingDTO bookingDTO) {
        if (authorizationController.isAuthorized(bookingDTO.getJws())) {
            Booking booking = new Booking();
            booking.setPax(bookingDTO.getPax());
            booking.setUser(userRepository.findById(bookingDTO.getUserId()).get());
            booking.setRestaurant(restaurantRepository.findById(bookingDTO.getRestaurantId()).get());
            booking.setRestaurantTable(restaurantTableRepository.findById(bookingDTO.getTableId()).get());
            booking.setDate(bookingDTO.getDate());
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new NotLoggedInException();
        }
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
        //ist ein Restaurant eingegeben - würde ich weglassen, da beim Eintrag des Restaurants ohnehin geschaut wird, dass das laut Tisch-ID hinterlegte Restaurant in der DB gespeichert wird.
        //Schreiben in die DB
        bookingRepository.save(updatedBooking);
        System.out.println("Die Änderung der Buchung sollte erfasst sein.");
        return updatedBooking;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte die Buchung mit der ID " + id + " gelöscht worden sein.");
        bookingRepository.deleteById(id);
    }
}