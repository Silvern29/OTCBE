package otc.be.controller;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import otc.be.config.Utils;
import otc.be.dto.Picture;
import otc.be.dto.RestaurantDTO;
import otc.be.entity.Restaurant;
import otc.be.repository.RestaurantRepository;

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

    public Iterable<Restaurant> getRestaurantWithFragment(String fragment) {return restaurantRepository.findAllWithFragment(fragment);}


    public void create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        System.out.println("Jetzt sollte ein neues Restaurant in der Tabelle Restaurant eingetragen worden sein.");
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
//        System.out.println(("Jetzt sollten die Restaurantdaten zur ID" + updatedRestaurant.getId() + " geändert worden sein"));
//        return updatedRestaurant;
//    }

    public Restaurant update(Restaurant restaurant) {
        if(restaurantRepository.findById(restaurant.getId()).isPresent()) {
            Restaurant newRestaurant = restaurantRepository.findById(restaurant.getId()).get();
            BeanUtils.copyProperties(restaurant, newRestaurant, Utils.getNullPropertyNames(restaurant));
            return restaurantRepository.save(newRestaurant);
        } else {
            return null;
        }
    }

//    public String addPictures(Restaurant restaurant, List<Picture> pictures) {
//        RestaurantDTO restaurantDTO = new RestaurantDTO()
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(pictures);
//        restaurant.setPictures(pictures);
//        return jsonString;
//    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte das Restaurant mit der ID " + id + " gelöscht worden sein.");
        restaurantRepository.deleteById(id);
    }
}