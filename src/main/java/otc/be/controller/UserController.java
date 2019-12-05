package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.User;
import otc.be.repository.BookingRepository;
import otc.be.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;  //für die Abfrage - alle Buchungen eines Users in Zukunft (Vorarbeit für das Stornieren von Buchungen)

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
        } else {
            String firstName = "Es gibt keinen User mit der ID " + id;
            System.out.println(firstName);
            return new User(firstName, "Quelle: getUserById", "", "");
        }
    }

    public void create(User user) {
        userRepository.save(user);
        System.out.println("Jetzt sollte ein neuer User in der Tabelle User eingetragen worden sein.");
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
            String firstName = "Es gibt keinen User mit der ID " + user.getId();
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
            String firstName = "Es gibt keinen User mit der ID " + id;
            System.out.println(firstName);
            return new User(firstName, "Quelle: deleteById", "", "");
        }
    }

    public User login(User user) {
        Optional<User> optionalCurrentUser = userRepository.findById(user.getId());
        if (optionalCurrentUser.isPresent()) {
            User currentUser = optionalCurrentUser.get();
            System.out.print("Der User: " + currentUser.getFirstName() + " " + currentUser.getLastName() + " mit der ID " + currentUser.getId() + " hat sein Passwort ");
            if (currentUser.getPassword().equals(user.getPassword())) {
                System.out.println("richtig eingegeben -> LOGGED IN");
                currentUser.setPassword("******");
                //String jws = Jwts.bu
                return currentUser;
            } else {
                System.out.println("nicht richtig eingegeben -> not permitted.");
                return new User("Passwort nicht richtig eingegeben", "Quelle: login", "", "");
            }
        } else {
            System.out.println("Es gibt keinen User mit der ID " + user.getId());
            String firstName = "Es gibt keinen User mit der ID " + user.getId();
            return new User(firstName, "Quelle: login", "", "");
        }
    }

    public LinkedList<Booking> allBookingsInFuture(Integer id) { //user-id prüfen
        Optional<User> optionalCurrentUser = userRepository.findById(id);
        LinkedList<Booking> allBookings = new LinkedList<>();
        if (optionalCurrentUser.isPresent()) {
            Date datum = Date.valueOf(LocalDate.now());
            Time uhrzeit = Time.valueOf(LocalTime.now());
            System.out.println("Zu Kontrollzwecken: " + datum + " " + uhrzeit);
            allBookings = bookingRepository.getListAllBookingsinFuture(id, datum, uhrzeit);
            if (allBookings.size() == 0) {
                String meldung = "Für den User " + id + " wurden keine in Zukunft liegenden Buchungen gefunden";
                System.out.println(meldung);
                User tempUser = new User();
                tempUser.setFirstName(meldung);
                Booking tempBooking = new Booking();
                tempBooking.setUser(tempUser);
                allBookings.add(tempBooking);
            } else {
                //zu Entwicklungszwecken
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy /  HH:mm");
                for (int i = 0; i < allBookings.size(); i++) {
                    Booking temp = allBookings.get(i); //für vereinfachte Ausgabe
                    int anfrageTag = temp.getDate().getDay() + 1;
                    int anfrageMonat = temp.getDate().getMonth() + 1;
                    int anfrageJahr = temp.getDate().getYear() + 1900;
                    int anfrageStunde = temp.getTime().getHours();
                    int anfrageMinute = temp.getTime().getMinutes();
                    LocalDateTime ldtBuchung = LocalDateTime.of(anfrageJahr, anfrageMonat, anfrageTag, anfrageStunde, anfrageMinute);
                    System.out.println("Booking-ID: " + temp.getId() + ", User-ID: " + temp.getUser() + ", Rest-ID: " + temp.getRestaurant().getId() + " " + temp.getRestaurant().getName() + ", Tisch-ID: " + temp.getRestaurantTable().getId() + " am " + ldtBuchung.format(dtf));
                }
            }
        } else {
            String meldung = "Es gibt keinen User mit der ID " + id;
            System.out.println(meldung);
            User tempUser = new User();
            tempUser.setFirstName(meldung);
            Booking tempBooking = new Booking();
            tempBooking.setUser(tempUser);
            allBookings.add(tempBooking);
        }
        return allBookings;
    }
}