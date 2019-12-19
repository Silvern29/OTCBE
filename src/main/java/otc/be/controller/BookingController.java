package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.dto.BookingDTO;
import otc.be.entity.Booking;
import otc.be.exception.NotLoggedInException;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantRepository;
import otc.be.repository.RestaurantTableRepository;
import otc.be.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private AdminController adminController;

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findByOrderByIdAsc();
    }

    public Iterable<Booking> getFutureBookingsByResId(int id, String jws) {
        if (adminController.isAuthorized(jws)) {
            return bookingRepository.getFutureBookingsByRestaurant(id, LocalDateTime.now());
        } else {
            throw new NotLoggedInException();
        }
    }

    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Booking create(BookingDTO bookingDTO) {
        if (authorizationController.isAuthorized(bookingDTO.getJws()) || adminController.isAuthorized(bookingDTO.getJws())) {
            Booking booking = new Booking();
            booking.setPax(bookingDTO.getPax());
            booking.setUser(userRepository.findById(bookingDTO.getUserId()).get());
            booking.setRestaurant(restaurantRepository.findById(bookingDTO.getRestaurantId()).get());
            booking.setRestaurantTable(restaurantTableRepository.findById(bookingDTO.getTableId()).get());
            booking.setLocalDateTime(bookingDTO.getLocalDateTime());
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new NotLoggedInException();
        }
    }

    public Booking update(BookingDTO bookingDTO) {
        if (adminController.isAuthorized(bookingDTO.getJws())) {
            Booking updatedBooking = bookingRepository.findById(bookingDTO.getId()).get();
            if (bookingDTO.getLocalDateTime() != null) updatedBooking.setLocalDateTime(bookingDTO.getLocalDateTime());
            if (bookingDTO.getUserId() > 0)
                updatedBooking.setUser(userRepository.findById(bookingDTO.getUserId()).get());
            if (bookingDTO.getTableId() > 0)
                updatedBooking.setRestaurantTable(restaurantTableRepository.findById(bookingDTO.getTableId()).get());
            if (bookingDTO.getRestaurantId() > 0)
                updatedBooking.setRestaurant(restaurantRepository.findById(bookingDTO.getRestaurantId()).get());
            if (bookingDTO.getPax() > 0) updatedBooking.setPax(bookingDTO.getPax());
            return bookingRepository.save(updatedBooking);
        } else {
            throw new NotLoggedInException();
        }
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte die Buchung mit der ID " + id + " gelöscht worden sein.");
        bookingRepository.deleteById(id);
    }
}