package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.AuthorizationController;
import otc.be.controller.UserController;
import otc.be.dto.AuthorizedUserDTO;
import otc.be.dto.LoginDTO;
import otc.be.dto.UserDTO;
import otc.be.entity.Booking;
import otc.be.entity.User;
import otc.be.exception.EmailExistsException;
import otc.be.exception.NoBookingsException;
import otc.be.exception.NoUserException;

import java.util.LinkedList;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class UserAPI {

    @Autowired
    private UserController userController;
    @Autowired
    private AuthorizationController authorizationController;

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

    @RequestMapping(method = RequestMethod.POST, path = "/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorizedUserDTO login(@RequestBody LoginDTO loginDTO) {
        return authorizationController.login(loginDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getListAll() {
        return userController.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userController.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO create(@RequestBody UserDTO user) throws EmailExistsException {
        userController.create(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable("id") Integer id) {
        userController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/users")
    public User update(@RequestBody User user) {
        return userController.update(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/allBookingsInFuture/{id}")
    public LinkedList<Booking> allBookingsInFuture(@PathVariable("id") Integer id) throws NoUserException, NoBookingsException {
        return userController.allBookingsInFuture(id);
    }
}