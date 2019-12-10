package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.AdminController;
import otc.be.controller.AuthorizationController;
import otc.be.dto.AuthorizedUserDTO;
import otc.be.dto.LoginDTO;
import otc.be.dto.UserDTO;
import otc.be.entity.Admin;
import otc.be.entity.Booking;
import otc.be.entity.User;
import otc.be.exception.EmailExistsException;

import java.util.LinkedList;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class AdminAPI {

    @Autowired
    private AdminController adminController;
    @Autowired
    private AuthorizationController authorizationController;

    @RequestMapping(method = RequestMethod.POST, path = "/admins/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Admin login(@RequestBody LoginDTO loginDTO) {
        return adminController.login(loginDTO);
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/admins", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Iterable<Admin> getListAll() {
//        return adminController.getAllAdmins();
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/admins/{id}")
//    public Admin getUserById(@PathVariable("id") Integer id) {
//        return adminController.getUserById(id);
//    }

    @RequestMapping(method = RequestMethod.POST, path = "/admins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Admin create(@RequestBody Admin admin) throws EmailExistsException {
        adminController.create(admin);
        return admin;
    }

//    @RequestMapping(method = RequestMethod.DELETE, path = "/admins/{id}")
//    public ResponseEntity<Admin> deleteById(@PathVariable("id") Integer id) {
//        adminController.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @RequestMapping(method = RequestMethod.PUT, path = "/users")
//    public User update(@RequestBody User user) {
//        return adminController.update(user);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/users/allBookingsInFuture/{id}")
//    public LinkedList<Booking> allBookingsInFuture(@PathVariable("id") Integer id) {
//        LinkedList allBookings = adminController.allBookingsInFuture(id);
//        return allBookings;
//    }
}
