package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.UserController;
import otc.be.entity.Booking;
import otc.be.entity.User;

import java.util.LinkedList;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class UserAPI {

    @Autowired
    private UserController userController;
//    UserRepository userRepository;

//    @RequestMapping(value="/users", method = RequestMethod.GET)
//    List<User> getAllUsers(@RequestParam(required = false) String id){
//        List<User> users = new LinkedList<>();
//        if(id == null) {
//            userRepository.findAll().forEach(users::add);
//        } else {
//            User user = userRepository.findById(Integer.parseInt(id)).get();
//            if(user != null) {
//                users.add(user);
//            }
//        }
//        return users;
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getListAll() {
        Iterable<User> listAll = userController.getAllUsers();
        return listAll;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userController.getUserById(id);
    }
//    vorherige Version
//    public ResponseEntity getUserById(@PathVariable("id") Integer id) {
//        Optional<User> user = userController.getUserById(id);
//        if(user.isPresent()){
//            return new ResponseEntity(user.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(method = RequestMethod.POST, path = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        userController.create(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
    public User deleteById(@PathVariable("id") Integer id) {
        User user = userController.deleteById(id);
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/users")
    public User update(@RequestBody User user) {
        User returnedUser = userController.update(user);
        return returnedUser;
    }

//    vorherige Version
//    public ResponseEntity update(@RequestBody User user) {
//        User returnedUser = userController.update(user);
//        if(returnedUser != null) {
//            return new ResponseEntity<>(returnedUser, HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(method = RequestMethod.POST, path = "/users/login")
    public ResponseEntity login(@RequestBody User user) {
        User returnedUser = userController.login(user);
        if(returnedUser != null) {
            return new ResponseEntity<>(returnedUser, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/allBookingsInFuture/{id}")
    public LinkedList<Booking> allBookingsInFuture(@PathVariable("id") Integer id) {
        LinkedList allBookings = userController.allBookingsInFuture(id);
        return allBookings;
    }
}