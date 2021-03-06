package otc.be.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otc.be.controller.RestaurantTableController;
import otc.be.entity.RestaurantTable;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class RestaurantTableAPI {

    @Autowired
    private RestaurantTableController restaurantTableController;

    @RequestMapping(method = RequestMethod.GET, path = "/tables")
    public Iterable<RestaurantTable> getListAll() {
        return restaurantTableController.getAllRestaurantTables();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tables/{id}")
    public Optional<RestaurantTable> getRestaurantTableById(@PathVariable("id") Integer id) {
        return restaurantTableController.getRestaurantTableById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/tables", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantTable create(@RequestBody RestaurantTable restaurantTable) {
        restaurantTableController.create(restaurantTable);
        return restaurantTable;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/tables")
    public RestaurantTable update(@RequestBody RestaurantTable restaurantTable) {
        restaurantTableController.update(restaurantTable);
        return restaurantTable;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/tables/{id}")
    public ResponseEntity<RestaurantTable> deletebyId(@PathVariable("id") Integer id) {
        restaurantTableController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}