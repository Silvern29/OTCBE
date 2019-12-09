package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.RestaurantController;
import otc.be.entity.Restaurant;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class RestaurantAPI {

    @Autowired
    private RestaurantController restaurantController;

    @RequestMapping(method = RequestMethod.GET, path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Restaurant> getListAll() {
        return restaurantController.getAllRestaurants();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Restaurant restaurant) {
        restaurantController.create(restaurant);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/restaurants")
    public Restaurant update(@RequestBody Restaurant restaurant) {
        restaurantController.update(restaurant);
        return restaurant;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/restaurants/{id}")
    public ResponseEntity<Restaurant> deletebyId(@PathVariable("id") Integer id) {
        restaurantController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurants/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable("id") Integer id) {
        return restaurantController.getRestaurantById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurants/search/{fragment}")
    public Iterable<Restaurant> getRestaurantById(@PathVariable("fragment") String fragment) {
        return restaurantController.getRestaurantWithFragment(fragment);
    }
}