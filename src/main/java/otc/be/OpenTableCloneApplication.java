package otc.be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import otc.be.controller.RestaurantController;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantRepository;
import otc.be.repository.RestaurantTableRepository;
import otc.be.repository.UserRepository;

@SpringBootApplication
public class OpenTableCloneApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantController restaurantController;
    @Autowired
    RestaurantTableRepository restaurantTableRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenTableCloneApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
//            Restaurant restaurant = new Restaurant();
//            restaurant.setId(45);
//            restaurant.setPictures(pictures);
//            restaurantController.update(restaurant);
//            userRepository.deleteAll();
//            restaurantRepository.deleteAll();
//            restaurantTableRepository.deleteAll();
//            bookingRepository.deleteAll();

//            User u1 = new User("Anton", "Ameise", "anton.ameise@aol.at", "abcdef");
//            User u2 = new User("Bernd", "Bande", "bernd.bande@gmx.at", "aaaaaa");
//            User u3 = new User("Clara", "Chor", "clara.chor@drei.at", "1111");
//            userRepository.save(u1);
//            userRepository.save(u2);
//            userRepository.save(u3);
//
//            Restaurant r1 = new Restaurant("Gasthaus Rössle", "gutbürgerlich", "Rautenastraße", "28", "6832", "Röthis", "teuer, wegen Reichtum öfter geschlossen, feiertags geschlossen");
//            Restaurant r2 = new Restaurant("Babbo Bar", "italienisch", "Donaustraße", "103", "12043", "Berlin", "authentisch, für Rendezvous geeignet, beachtliche Weinkarte");
//            Restaurant r3 = new Restaurant("Chotto Motto", "kreative Küche", "Frith Street", "11-13", "W1D 4RB", "London", "für Rendezvous geeignet, besondere Anlässe");
//            restaurantRepository.save(r1);
//            restaurantRepository.save(r2);
//            restaurantRepository.save(r3);

//            Restaurant or = restaurantRepository.findById(45).get(); //or = Optional<Restaurant> = Rückgabeformat von findById()
//            System.out.println(or.getId() + " " + or.getName());
//            RestaurantTable t1 = new RestaurantTable(1, 4, or);
//            RestaurantTable t2 = new RestaurantTable(2, 4, or);
//            RestaurantTable t3 = new RestaurantTable(3, 4, or);
//            RestaurantTable t4 = new RestaurantTable(4, 4, or);
//            RestaurantTable t5 = new RestaurantTable(5, 2, or);
//            RestaurantTable t6 = new RestaurantTable(6, 2, or);
//            RestaurantTable t7 = new RestaurantTable(7, 6, or);
//            RestaurantTable t8 = new RestaurantTable(8, 6, or);
//            RestaurantTable t9 = new RestaurantTable(9, 8, or);
//            RestaurantTable t10 = new RestaurantTable(10, 8, or);
//            restaurantTableRepository.save(t1);
//            restaurantTableRepository.save(t2);
//            restaurantTableRepository.save(t3);
//            restaurantTableRepository.save(t4);
//            restaurantTableRepository.save(t5);
//            restaurantTableRepository.save(t6);
//            restaurantTableRepository.save(t7);
//            restaurantTableRepository.save(t8);
//            restaurantTableRepository.save(t9);
//            restaurantTableRepository.save(t10);
//            or = restaurantRepository.findById(46).get(); //or = Optional<Restaurant> = Rückgabeformat von findById()
//            System.out.println(or.getId() + " " + or.getName());
//            t1 = new RestaurantTable(1, 8, or);
//            t2 = new RestaurantTable(2, 8, or);
//            t3 = new RestaurantTable(3, 6, or);
//            t4 = new RestaurantTable(4, 6, or);
//            t5 = new RestaurantTable(5, 4, or);
//            t6 = new RestaurantTable(6, 4, or);
//            t7 = new RestaurantTable(7, 4, or);
//            t8 = new RestaurantTable(8, 4, or);
//            t9 = new RestaurantTable(9, 4, or);
//            t10 = new RestaurantTable(10, 4, or);
//            restaurantTableRepository.save(t1);
//            restaurantTableRepository.save(t2);
//            restaurantTableRepository.save(t3);
//            restaurantTableRepository.save(t4);
//            restaurantTableRepository.save(t5);
//            restaurantTableRepository.save(t6);
//            restaurantTableRepository.save(t7);
//            restaurantTableRepository.save(t8);
//            restaurantTableRepository.save(t9);
//            restaurantTableRepository.save(t10);
//            or = restaurantRepository.findById(47).get(); //or = Optional<Restaurant> = Rückgabeformat von findById()
//            t1 = new RestaurantTable(1, 5, or);
//            t2 = new RestaurantTable(2, 5, or);
//            t3 = new RestaurantTable(3, 4, or);
//            t4 = new RestaurantTable(4, 4, or);
//            t5 = new RestaurantTable(5, 3, or);
//            t6 = new RestaurantTable(6, 3, or);
//            t7 = new RestaurantTable(7, 2, or);
//            t8 = new RestaurantTable(8, 2, or);
//            t9 = new RestaurantTable(9, 2, or);
//            t10 = new RestaurantTable(10, 2, or);
//            restaurantTableRepository.save(t1);
//            restaurantTableRepository.save(t2);
//            restaurantTableRepository.save(t3);
//            restaurantTableRepository.save(t4);
//            restaurantTableRepository.save(t5);
//            restaurantTableRepository.save(t6);
//            restaurantTableRepository.save(t7);
//            restaurantTableRepository.save(t8);
//            restaurantTableRepository.save(t9);
//            restaurantTableRepository.save(t10);

//            User bu1 = userRepository.findById(41).get(); //User mit ID für die zu erstellende Buchung auslesen
//            RestaurantTable brt1 = restaurantTableRepository.findById(34).get(); // Tisch mit ID für die zu erstellende Buchung auslesen
//            Restaurant br1 = brt1.getRestaurant();
//            Booking b1 = new Booking(bu1, br1, brt1, Date.valueOf("2019-12-03"), Time.valueOf(LocalTime.now()));
//            Booking b2 = new Booking(bu1, br1, brt1, Date.valueOf("2019-12-02"), Time.valueOf(LocalTime.now()));
//            Booking b3 = new Booking(bu1, br1, brt1, Date.valueOf("2019-12-01"), Time.valueOf(LocalTime.now()));
//            bookingRepository.save(b3);
//            bookingRepository.save(b2);
//            bookingRepository.save(b1);
        };
    }
}
