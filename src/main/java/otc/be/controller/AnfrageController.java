package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import otc.be.entity.Anfrage;
import otc.be.entity.RestaurantTable;
import otc.be.repository.AnfrageRepository;
import otc.be.repository.RestaurantTableRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
test
@Transactional
@Controller
public class AnfrageController {
    @Autowired
    private AnfrageRepository anfrageRepository;                 //standardmäßiger Aufruf
    @Autowired
    private RestaurantTableRepository restaurantTableRepository; //Suchanfragen an die DB
    @Autowired
    private RestaurantTableController restaurantTableController; //Aufruf einer Funktion


    public Anfrage step1(Anfrage anfrage) {
        System.out.print("Die Anfrage lautet: Kann im Restaurant ID " + anfrage.getId_restaurant() + " für " + anfrage.getPersonenzahl() + " Personen ");
        int anfrageTag = anfrage.getDatum().getDay() + 1;
        int anfrageMonat = anfrage.getDatum().getMonth() + 1;
        int anfrageJahr = anfrage.getDatum().getYear() + 1900;
        int anfrageStunde = anfrage.getUhrzeit().getHours();
        int anfrageMinute = anfrage.getUhrzeit().getMinutes();
        LocalDateTime ldtAnfrage = LocalDateTime.of(anfrageJahr, anfrageMonat, anfrageTag, anfrageStunde, anfrageMinute);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy /  HH:mm");
        System.out.println(" am " + ldtAnfrage.format(dtf) + " Uhr ein Tisch reserviert werden?");
        boolean buchungMöglich;
        String meldung = "";
        anfrage.setBemerkung("");
        //Ermittlung der in Frage kommenden Tische im angegebenen Restaurant
        LinkedList<RestaurantTable> possibleTables = restaurantTableRepository.getListTablesPAX(anfrage.getId_restaurant(), anfrage.getPersonenzahl());
        if (possibleTables.size() > 0) {
            //Ausgabe der gefundenen Tische zur Info - ohne weitere Fkt. - während der Entwicklung
            for (int i = 0; i < possibleTables.size(); i++) {
                System.out.println(possibleTables.get(i).getRestaurant().getId() + " " + possibleTables.get(i).getRestaurant().getName() + " Rest.-Tisch-Nr.:" + possibleTables.get(i).getTableNrRest() + ", Tisch-Id:" + possibleTables.get(i).getId() + ", PAX: " + possibleTables.get(i).getPax());
            }
            for (int i = 0; i < possibleTables.size(); i++) {
                System.out.print("Ergebnis für Tisch-ID: " + possibleTables.get(i).getId() + " ");
                buchungMöglich = restaurantTableController.getRestaurantTableByIdIsFree(possibleTables.get(i).getId(), ldtAnfrage);
                if (buchungMöglich) {
                    System.out.println("Der Tisch kann gebucht werden.");
                    anfrage.setBuchungMöglich(true);
                    anfrage.setId_table(possibleTables.get(i).getId());
                    i = possibleTables.size(); //kein weiterer Suchlauf beim nächsten Tisch
                } else {
                    meldung = anfrage.getBemerkung();
                    meldung = meldung + "Tisch-ID " + possibleTables.get(i).getId() + " ist bereits gebucht; ";
                    anfrage.setBemerkung(meldung);
                }
            }
        } else {
            meldung = "Keine Tische für diese Personenzahl im gewünschten Restaurant auffindbar.";
            System.out.println(meldung);
            anfrage.setBemerkung(meldung);
            //in der Anfrage, die über die API retouniert wird,  bleibt id_table auf 0 und buchungMöglich auch auf false
        }
        System.out.println("Die zurückgegebene Anfrage schaut so aus:");
        System.out.print("Im Restaurant ID " + anfrage.getId_restaurant() + " kann für " + anfrage.getPersonenzahl() + " Personen  am " + ldtAnfrage.format(dtf) + " Uhr der Tisch mit der ID " + anfrage.getId_table());
        if (!anfrage.isBuchungMöglich()) System.out.print(" nicht");
        System.out.println(" gebucht werden, Bemerkung: " + anfrage.getBemerkung());
        return anfrage;
    }
}
