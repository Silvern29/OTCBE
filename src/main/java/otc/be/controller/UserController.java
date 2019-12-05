package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import otc.be.dto.UserDTO;
import otc.be.entity.User;
import otc.be.exception.EmailExistsException;
import otc.be.repository.UserRepository;

import java.util.LinkedList;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        LinkedList<User> allUsers = userRepository.findByOrderByIdAsc();
        for (User allUser : allUsers) {
            allUser.setPassword("******");
        }
        return allUsers;
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            currentUser.setPassword("******");
            return currentUser;
        }
        else{
            String firstName = "Es gibt keinen User mit der ID " +  id;
            System.out.println(firstName);
            return new User(firstName, "Quelle: getUserById", "", "");
        }
    }

    public User create(UserDTO accountDto) throws EmailExistsException {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        return userRepository.save(user);
//        System.out.println("Jetzt sollte ein neuer User in der Tabelle User eingetragen worden sein.");
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }


    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            if (!user.getFirstName().equals("")) updatedUser.setFirstName(user.getFirstName());
            if (!user.getLastName().equals("")) updatedUser.setLastName(user.getLastName());
            if (!user.getEmail().equals("")) updatedUser.setEmail(user.getEmail());
            if (!user.getPassword().equals("")) updatedUser.setPassword(user.getPassword());
            userRepository.save(updatedUser);
            System.out.println("Die Userdaten sollten auf ID " + updatedUser.getId() + " " + updatedUser.getFirstName() + " " + updatedUser.getLastName() + " " + updatedUser.getEmail() + " " + updatedUser.getPassword() + " geändert worden sein.");
            return updatedUser;
        } else {
            String firstName = "Es gibt keinen User mit der ID " +  user.getId();
            System.out.println(firstName);
            return new User(firstName, "Quelle: update", "", "");
        }
    }

    public User deleteById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            System.out.println("Nun sollte der User mit der ID " + id + " gelöscht worden sein.");
            userRepository.deleteById(id);
            return currentUser;
        } else {
            String firstName = "Es gibt keinen User mit der ID " +  id;
            System.out.println(firstName);
            return new User(firstName, "Quelle: deleteById", "", "");
        }
    }

//    public User login(User user) {
//        Optional<User> optionalCurrentUser = userRepository.findById(user.getId());
//        if (optionalCurrentUser.isPresent()) {
//            User currentUser = optionalCurrentUser.get();
//            System.out.print("Der User: " + currentUser.getFirstName() + " " + currentUser.getLastName() + " mit der ID " + currentUser.getId() + " hat sein Passwort ");
//            if (currentUser.getPassword().equals(user.getPassword())) {
//                System.out.println("richtig eingegeben -> LOGGED IN");
//                currentUser.setPassword("******");
//                //String jws = Jwts.bu
//                return currentUser;
//            } else {
//                System.out.println("nicht richtig eingegeben -> not permitted.");
//                return new User("Passwort nicht richtig eingegeben", "Quelle: login", "", "");
//            }
//        } else {
//            System.out.println("Es gibt keinen User mit der ID " + user.getId());
//            String firstName = "Es gibt keinen User mit der ID " +  user.getId();
//            return new User(firstName, "Quelle: login", "", "");
//        }
//    }
}