package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.UserController;
import otc.be.entity.Restaurant;
import otc.be.entity.User;
import otc.be.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class UserAPI {

    @Autowired
    UserController userController;
    UserRepository userRepository;

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
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        Optional<User> user = userController.getUserById(id);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
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
        userController.update(user);
        return user;
    }
}