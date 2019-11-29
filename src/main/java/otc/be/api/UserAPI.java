package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import otc.be.entity.User;
import otc.be.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
public class UserAPI {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    List<User> getAllUsers(@RequestParam(required = false) String id){
        List<User> users = new LinkedList<>();
        if(id == null) {
            userRepository.findAll().forEach(users::add);
        } else {
            User user = userRepository.findById(Integer.parseInt(id)).get();
            if(user != null) {
                users.add(user);
            }
        }
        return users;
    }
}
