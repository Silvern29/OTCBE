package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import otc.be.dto.UserDTO;
import otc.be.entity.Booking;
import otc.be.entity.User;
import otc.be.exception.EmailExistsException;
import otc.be.exception.NoBookingsException;
import otc.be.exception.NoUserException;
import otc.be.exception.NotLoggedInException;
import otc.be.repository.BookingRepository;
import otc.be.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorizationController authorizationController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findByOrderByIdAsc();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User create(UserDTO accountDto) throws EmailExistsException {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        //Passwordencoder hashes the password using bcrypt
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }


    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            if (user.getFirstName() != null) updatedUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) updatedUser.setLastName(user.getLastName());
            if (user.getEmail() != null) updatedUser.setEmail(user.getEmail());
            if (user.getPassword() != null) updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            throw new NoUserException("User not found");
        }
    }

    public User deleteById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            userRepository.deleteById(id);
            return currentUser;
        } else {
            throw new NoUserException("User not found");
        }
    }

    public LinkedList<Booking> usersBookingsInFuture(int id, String jws) {
        if (authorizationController.isAuthorized(jws)) {
            if (userRepository.findById(id).isPresent()) {
                LinkedList<Booking> allBookings = bookingRepository.getFutureBookingsByUser(id, LocalDateTime.now());
                if (allBookings.size() > 0) {
                    return allBookings;
                } else {
                    throw new NoBookingsException("No bookings found for user");
                }
            } else {
                throw new NoUserException("User not found");
            }
        } else {
            throw new NotLoggedInException();
        }
    }

    public LinkedList<Booking> usersBookingsInPast(int id, String jws) {
        if (authorizationController.isAuthorized(jws)) {
            if (userRepository.findById(id).isPresent()) {
                LinkedList<Booking> allBookings = bookingRepository.getPastBookingsByUser(id, LocalDateTime.now());
                if (allBookings.size() > 0) {
                    return allBookings;
                } else {
                    throw new NoBookingsException("No bookings found for user");
                }
            } else {
                throw new NoUserException("User not found");
            }
        } else {
            throw new NotLoggedInException();
        }
    }
}