package otc.be.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.config.Utils;
import otc.be.entity.OpeningTime;
import otc.be.entity.Restaurant;
import otc.be.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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

    public Iterable<Restaurant> getRestaurantWithFragment(String fragment) {
        return restaurantRepository.findAllWithFragment(fragment);
    }


    public void create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public boolean isOpen(LocalDateTime ldt, Restaurant restaurant){
        List<OpeningTime> matchingDays = new LinkedList<>();
        for (OpeningTime openingTime : restaurant.getOpeningHours()){
            if (ldt.getDayOfWeek().equals(openingTime.getDayOfWeek())){
                matchingDays.add(openingTime);
            }
        }
        for (OpeningTime openingTime : restaurant.getOpeningHours()) {

        }
        return true;
    }

//    public Restaurant update(Restaurant restaurant) {
//        Restaurant updatedRestaurant = restaurantRepository.findById(restaurant.getId()).get();
//        if (restaurant.getName() != null) updatedRestaurant.setName(restaurant.getName());
//        if (restaurant.getKitchen() != null) updatedRestaurant.setKitchen(restaurant.getKitchen());
//        if (restaurant.getStreet() != null) updatedRestaurant.setStreet(restaurant.getStreet());
//        if (restaurant.getApNr() != null) updatedRestaurant.setApNr(restaurant.getApNr());
//        if (restaurant.getZip() != null) updatedRestaurant.setZip(restaurant.getZip());
//        if (restaurant.getCity() != null) updatedRestaurant.setCity(restaurant.getCity());
//        if (restaurant.getInfo() != null) updatedRestaurant.setInfo(restaurant.getInfo());
//        restaurantRepository.save(updatedRestaurant);
//        return updatedRestaurant;
//    }

    public Restaurant update(Restaurant restaurant) {
        if (restaurantRepository.findById(restaurant.getId()).isPresent()) {
            Restaurant newRestaurant = restaurantRepository.findById(restaurant.getId()).get();
            BeanUtils.copyProperties(restaurant, newRestaurant, Utils.getNullPropertyNames(restaurant));
            return restaurantRepository.save(newRestaurant);
        } else {
            return null;
        }
    }

    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }
}