package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import otc.be.entity.Restaurant;
import otc.be.repository.RestaurantRepository;

import java.util.Optional;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findByOrderByIdAsc();
    }

    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }

    public Iterable<Restaurant> getRestaurantWithFragment(String fragment) {return restaurantRepository.findAllWithFragment(fragment);}


    public void create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        System.out.println("Jetzt sollte ein neues Restaurant in der Tabelle Restaurant eingetragen worden sein.");
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantRepository.findById(restaurant.getId()).get();
        if (!restaurant.getName().equals("")) updatedRestaurant.setName(restaurant.getName());
        if (!restaurant.getKitchen().equals("")) updatedRestaurant.setKitchen(restaurant.getKitchen());
        if (!restaurant.getStreet().equals("")) updatedRestaurant.setStreet(restaurant.getStreet());
        if (!restaurant.getApNr().equals("")) updatedRestaurant.setApNr(restaurant.getApNr());
        if (!restaurant.getZip().equals("")) updatedRestaurant.setZip(restaurant.getZip());
        if (!restaurant.getCity().equals("")) updatedRestaurant.setCity(restaurant.getCity());
        if (!restaurant.getInfo().equals("")) updatedRestaurant.setInfo(restaurant.getInfo());
        restaurantRepository.save(updatedRestaurant);
        System.out.println(("Jetzt sollten die Restaurantdaten zur ID" + updatedRestaurant.getId() + " geändert worden sein"));
        return updatedRestaurant;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte das Restaurant mit der ID " + id + " gelöscht worden sein.");
        restaurantRepository.deleteById(id);
    }
}