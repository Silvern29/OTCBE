package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import otc.be.entity.AnfrageDTO;
import otc.be.entity.RestaurantTable;
import otc.be.repository.RestaurantTableRepository;

import java.time.LocalDateTime;
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

    public LinkedList<AnfrageDTO> buildList(AnfrageDTO anfrageDTO) {
        LinkedList<AnfrageDTO> antwortliste = new LinkedList<>();

        AnfrageDTO antwort = new AnfrageDTO();
        antwort.setPersonenzahl(anfrageDTO.getPersonenzahl());
        antwort.setLocalDateTime(anfrageDTO.getLocalDateTime());

        if (anfrageDTO.getId_restaurant() == 0) {
            LinkedList<Integer> inFrageKommendeRestaurants = restaurantTableRepository.getRestaurantTablesByPax(anfrageDTO.getPersonenzahl());
            if (inFrageKommendeRestaurants.size() > 0) {
                for (int i = 0; i < inFrageKommendeRestaurants.size(); i++) {
                    antwort.setId_restaurant(inFrageKommendeRestaurants.get(i));
                    antwort = buildDTO(antwort);

                    if (restaurantController.isOpen(anfrageDTO.getLocalDateTime(), restaurantController.getRestaurantById(inFrageKommendeRestaurants.get(i)).get()) && antwort.isBuchungMoeglich()) {
                        antwortliste.add(antwort);
                    }
                }
                if (antwortliste.size() == 0) {
                    antwort.setBuchungMoeglich(false);
                    antwortliste.add(antwort);
                }
            } else {
                antwort.setBuchungMoeglich(false);
                antwortliste.add(antwort);
            }
        } else {
            antwort = buildDTO(anfrageDTO);
            antwortliste.add(antwort);
        }
        return antwortliste;
    }

    public AnfrageDTO buildDTO(AnfrageDTO anfrageDTO) {
        LocalDateTime ldtAnfrage = anfrageDTO.getLocalDateTime();
        boolean bookingPoss;
        LinkedList<RestaurantTable> possibleTables = restaurantTableRepository.getListTablesPAX(anfrageDTO.getId_restaurant(), anfrageDTO.getPersonenzahl());
        if (possibleTables.size() > 0) {
            for (int i = 0; i < possibleTables.size(); i++) {
                bookingPoss = restaurantTableController.getRestaurantTableByIdIsFree(possibleTables.get(i).getId(), ldtAnfrage);
                if (bookingPoss) {
                    anfrageDTO.setBuchungMoeglich(true);
                    anfrageDTO.setId_table(possibleTables.get(i).getId());
                    i = possibleTables.size();
                }
            }
        }
        return anfrageDTO;
    }
}