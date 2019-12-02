package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otc.be.controller.BookingController;

@CrossOrigin
@RestController
@RequestMapping(path="api")
public class BookingAPI {

    @Autowired
    private BookingController bookingController;

}
