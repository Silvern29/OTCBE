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

    public LinkedList<AnfrageDTO> buildList(AnfrageDTO anfrageDTO) {
        LinkedList<AnfrageDTO> antwortliste = new LinkedList<>();
        if (anfrageDTO.getId_restaurant() == 0) {
            LinkedList<Integer> inFrageKommendeRestaurants = restaurantTableRepository.getRestaurantTablesByPax(anfrageDTO.getPersonenzahl());
            if (inFrageKommendeRestaurants.size() > 0) {
                for (int i = 0; i < inFrageKommendeRestaurants.size(); i++) {
                    AnfrageDTO tempAnfrageDTO = new AnfrageDTO();
                    tempAnfrageDTO.setPersonenzahl(anfrageDTO.getPersonenzahl());
                    tempAnfrageDTO.setId_restaurant(inFrageKommendeRestaurants.get(i));
                    tempAnfrageDTO.setLocalDateTime(anfrageDTO.getLocalDateTime());
                    AnfrageDTO antwort = buildDTO(tempAnfrageDTO);
                    if (antwort.isBuchungMoeglich()) {
                        antwortliste.add(antwort);
                    }
                }
                if (antwortliste.size() == 0) {
                    anfrageDTO.setBuchungMoeglich(false);
                    antwortliste.add(anfrageDTO);
                }
            } else {
                anfrageDTO.setBuchungMoeglich(false);
                antwortliste.add(anfrageDTO);
            }
        } else {
            AnfrageDTO antwort = buildDTO(anfrageDTO);
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