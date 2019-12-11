package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.BookingController;
import otc.be.dto.BookingDTO;
import otc.be.entity.Booking;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class BookingAPI {

    @Autowired
    private BookingController bookingController;

    @RequestMapping(method = RequestMethod.GET, path = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Booking> getListAll() {
        return bookingController.getAllBookings();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurants/{id}/bookings/{jws}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Booking> getListAll(@PathVariable int id, @PathVariable String jws) {
        return bookingController.getFutureBookingsByResId(id, jws);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookings/{id}")
    public Optional<Booking> getBookingById(@PathVariable("id") Integer id) {
        return bookingController.getBookingById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public Booking create(@RequestBody BookingDTO bookingDTO) {
        return bookingController.create(bookingDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/bookings")
    public Booking update(@RequestBody BookingDTO bookingDTO) {
        return bookingController.update(bookingDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/bookings/{id}")
    public ResponseEntity<Booking> deletebyId(@PathVariable("id") Integer id) {
        bookingController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}