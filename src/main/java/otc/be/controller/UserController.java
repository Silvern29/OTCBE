package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.User;
import otc.be.repository.UserRepository;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findByOrderByIdAsc();
    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void create(User user) {
        userRepository.save(user);
        System.out.println("Jetzt sollte ein neuer User in der Tabelle User eingetragen worden sein.");
    }

    public User update(User user) {
        User updatedUser = userRepository.findById((user.getId())).get();
        if (!user.getFirstName().equals("")) updatedUser.setFirstName(user.getFirstName());
        if (!user.getLastName().equals("")) updatedUser.setLastName(user.getLastName());
        if (!user.getEmail().equals("")) updatedUser.setEmail(user.getEmail());
        if (!user.getPassword().equals("")) updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
        System.out.println("Die Userdaten sollten auf "+ updatedUser.getId()+ " " + updatedUser.getFirstName() + " " + updatedUser.getLastName()+ " " + updatedUser.getEmail() + " " + updatedUser.getPassword() + " geändert worden sein.");
        return updatedUser;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der User mit der ID " + id + " gelöscht worden sein.");
        userRepository.deleteById(id);
    }
}