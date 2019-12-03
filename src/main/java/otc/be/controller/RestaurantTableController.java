package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import otc.be.entity.Restaurant;
import otc.be.entity.RestaurantTable;
import otc.be.repository.RestaurantTableRepository;

import java.util.Optional;

@Controller
public class RestaurantTableController {
    @Autowired
    RestaurantTableRepository restaurantTableRepository;


    public Iterable<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findByOrderByIdAsc();
    }

    public Optional<RestaurantTable> getRestaurantTableById(Integer id) {
        return restaurantTableRepository.findById(id);
    }

    public void create(RestaurantTable restaurantTable) {
        restaurantTableRepository.save(restaurantTable);
        System.out.println("Jetzt sollte ein neuer Tisch in der Tabelle RestaurantTable eingetragen worden sein.");
    }

    public RestaurantTable update(RestaurantTable restaurantTable) {
        RestaurantTable updatedRestaurantTable = restaurantTableRepository.findById(restaurantTable.getId()).get();
        if (updatedRestaurantTable.getTableNrRest() > 0) updatedRestaurantTable.setTableNrRest(restaurantTable.getTableNrRest());
        if (updatedRestaurantTable.getPax() > 0) updatedRestaurantTable.setPax(restaurantTable.getPax());
        if (updatedRestaurantTable.getRestaurant().getId() != restaurantTable.getRestaurant().getId()) updatedRestaurantTable.setRestaurant(restaurantTable.getRestaurant());
        restaurantTableRepository.save(updatedRestaurantTable);
        System.out.println("Der Tisch mit der ID " + updatedRestaurantTable.getId() + " ist auf Personenzahl: " + updatedRestaurantTable.getPax() + " Restaurant-ID" + updatedRestaurantTable.getRestaurant().getId() + " Rest.-Tisch-ID" + updatedRestaurantTable.getTableNrRest() + "geändert.");
        return updatedRestaurantTable;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der Tisch mit der ID " + id + " gelöscht worden sein.");
        restaurantTableRepository.deleteById(id);
    }

}
