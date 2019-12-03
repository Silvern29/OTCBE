package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.BookingController;
import otc.be.entity.Booking;


import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="api")
public class BookingAPI {

    @Autowired
    private BookingController bookingController;

    @RequestMapping(method = RequestMethod.GET, path = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Booking> getListAll() {
        Iterable<Booking> listAll = bookingController.getAllBookings();
        return listAll;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookings/{id}")
    public Optional<Booking> getBookingById(@PathVariable("id") Integer id) {
        Optional<Booking> booking = bookingController.getBookingById(id);
        return booking;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE) //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Booking booking) {
        bookingController.create(booking);
        //return booking;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/bookings")
    public Booking update(@RequestBody Booking booking) {
        bookingController.update(booking);
        return booking;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/bookings/{id}")
    public ResponseEntity<Booking> deletebyId(@PathVariable("id") Integer id) {
        bookingController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}