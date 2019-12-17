package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import otc.be.dto.AnfrageDTO;
import otc.be.entity.RestaurantTable;
import otc.be.repository.RestaurantTableRepository;

import java.util.LinkedList;

@Transactional
@Controller
public class AnfrageController {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private RestaurantTableController restaurantTableController;
    @Autowired
    private RestaurantController restaurantController;

    /**
     * Function takes in a request. If restaurant is already set, continues to requestRestaurant,
     * otherwise builds list with matching tables and iterates over it with requestRestaurant.
     * **/
    public LinkedList<AnfrageDTO> buildList(AnfrageDTO anfrageDTO) {
        LinkedList<AnfrageDTO> antwortliste = new LinkedList<>();
        anfrageDTO.setBuchungMoeglich(false);

        // Restaurant bereits gesetzt.
        if (anfrageDTO.getId_restaurant() != 0) {
            antwortliste.add(requestRestaurant(anfrageDTO));
            return antwortliste;
        }

        // Suche passende restaurants.
        LinkedList<Integer> inFrageKommendeRestaurants = restaurantTableRepository.getRestaurantTablesByPax(anfrageDTO.getPersonenzahl());
        if (inFrageKommendeRestaurants.size() > 0) {
            for (int i = 0; i < inFrageKommendeRestaurants.size(); i++) {
                anfrageDTO.setId_restaurant(inFrageKommendeRestaurants.get(i));
                anfrageDTO = requestRestaurant(anfrageDTO);

                if (anfrageDTO.isBuchungMoeglich()) {
                    antwortliste.add(anfrageDTO);
                }
            }
        }
        if (antwortliste.size() == 0) {
            antwortliste.add(anfrageDTO);
        }
        return antwortliste;
    }

    /**
     * Takes a request from buildList with specific restaurant and iterates over possible tables.
     * **/
    public AnfrageDTO requestRestaurant(AnfrageDTO anfrageDTO) {
        LinkedList<RestaurantTable> possibleTables = restaurantTableRepository.getListTablesPAX(anfrageDTO.getId_restaurant(), anfrageDTO.getPersonenzahl());
        if (restaurantController.isOpen(anfrageDTO.getLocalDateTime(), restaurantController.getRestaurantById(anfrageDTO.getId_restaurant()).get()) && possibleTables.size() > 0) {
            int i = 0;
            while(!anfrageDTO.isBuchungMoeglich() || i == possibleTables.size()) {
                if (restaurantTableController.getRestaurantTableByIdIsFree(possibleTables.get(i).getId(), anfrageDTO.getLocalDateTime())) {
                    anfrageDTO.setBuchungMoeglich(true);
                    anfrageDTO.setId_table(possibleTables.get(i).getId());
                }
            }
        }
        return anfrageDTO;
    }
}