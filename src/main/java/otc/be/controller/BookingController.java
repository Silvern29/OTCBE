package otc.be.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.config.Utils;
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
            booking.setTime(bookingDTO.getTime());
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new NotLoggedInException();
        }
    }

    public Booking update(BookingDTO bookingDTO) {
        Booking updatedBooking = bookingRepository.findById(bookingDTO.getId()).get();
        //ist ein Datum eingegeben?
        if (bookingDTO.getDate() != null) updatedBooking.setDate(bookingDTO.getDate());
        //ist eine Uhrzeit eingegeben?
        if (bookingDTO.getTime() != null) updatedBooking.setTime(bookingDTO.getTime());
        //ist ein User eingegeben?
        if (bookingDTO.getUserId() > 0) updatedBooking.setUser(userRepository.findById(bookingDTO.getUserId()).get());
        //ist ein Tisch eingegeben?
        if (bookingDTO.getTableId() > 0) updatedBooking.setRestaurantTable(restaurantTableRepository.findById(bookingDTO.getTableId()).get());
        if (bookingDTO.getRestaurantId() > 0) updatedBooking.setRestaurant(restaurantRepository.findById(bookingDTO.getRestaurantId()).get());
        if (bookingDTO.getPax() > 0) updatedBooking.setPax(bookingDTO.getPax());
        //Schreiben in die DB
        return bookingRepository.save(updatedBooking);
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte die Buchung mit der ID " + id + " gelöscht worden sein.");
        bookingRepository.deleteById(id);
    }
}