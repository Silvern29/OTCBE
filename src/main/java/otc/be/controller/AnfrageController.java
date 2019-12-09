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

@Transactional
@Controller
public class AnfrageController {
    @Autowired
    private AnfrageRepository anfrageRepository;                 //standardmäßiger Aufruf
    @Autowired
    private RestaurantTableRepository restaurantTableRepository; //Suchanfragen an die DB nach den in Frage kommenden Tischen eines bestimmten Restaurants
    @Autowired
    private RestaurantTableController restaurantTableController; //Aufruf einer Funktion (ist ein bestimmter Tisch zu einer bestimmten Zeit reservierbar)

    public LinkedList<Anfrage> step1(Anfrage anfrage) {
        System.out.println("------------"); //nur für die Übersichtlichkeit der Meldungen während der Entwicklung
        LinkedList<Anfrage> antwortliste = new LinkedList<>();
        if (anfrage.getId_restaurant() == 0) {
            System.out.println("Es ist noch kein Restaurant ausgewählt, es folgt die Abfrage der für die Personenzahl in Frage kommenden Tische (restaurantübergreifend)");

            LinkedList<Integer> inFrageKommendeRestaurants = restaurantTableRepository.getRestaurantTablesByPax(anfrage.getPersonenzahl());
            if (inFrageKommendeRestaurants.size() > 0) { //gibt es überhaupt Restaurants mit Tischen für die angefragte Personenzahl
                //Ausgabe der gefundenen Restaurants zur Info - ohne weitere Fkt. - während der Entwicklung
                for (int i = 0; i < inFrageKommendeRestaurants.size(); i++) {
                    System.out.println("In Frage kommende Restaurant-ID: " + inFrageKommendeRestaurants.get(i));
                }
                //Vorbereitung der Datums-Ausgabe - braucht es derweil nur für die Entwicklung
                int anfrageTag = anfrage.getDatum().getDay() + 1;
                int anfrageMonat = anfrage.getDatum().getMonth() + 1;
                int anfrageJahr = anfrage.getDatum().getYear() + 1900;
                int anfrageStunde = anfrage.getUhrzeit().getHours();
                int anfrageMinute = anfrage.getUhrzeit().getMinutes();
                LocalDateTime ldtAnfrage = LocalDateTime.of(anfrageJahr, anfrageMonat, anfrageTag, anfrageStunde, anfrageMinute);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy /  HH:mm");
                anfrage.setBemerkung("");
                for (int i = 0; i < inFrageKommendeRestaurants.size(); i++) {
                    //bauen der Anfrage für step2
                    Anfrage tempAnfrage = new Anfrage();
                    tempAnfrage.setPersonenzahl(anfrage.getPersonenzahl());
                    tempAnfrage.setId_restaurant(inFrageKommendeRestaurants.get(i));
                    tempAnfrage.setDatum(anfrage.getDatum());
                    tempAnfrage.setUhrzeit(anfrage.getUhrzeit());
                    System.out.println("Die aus der Schleife herausgegebene Anfrage lautet: Kann im Restaurant ID " + tempAnfrage.getId_restaurant() + " für " + tempAnfrage.getPersonenzahl() + " Personen  am " + ldtAnfrage.format(dtf) + " Uhr ein Tisch reserviert werden?");
                    Anfrage antwort = step2(tempAnfrage);
                    if (antwort.isBuchungMoeglich()) {//wenn im Restaurant ein reservierbarer Tisch gefunden wurde, wird dieser der Antwortliste angehangen, ansonsten wird die Antwort verworfen.
                    antwortliste.add(antwort);}
                }
                if(antwortliste.size()==0){ //waren alle in Frage kommenden Tische reserviert
                    String meldung = "Alle in Frage kommenden Tische sind zur angefragten Zeit bereits gebucht.";
                    System.out.println(meldung);
                    anfrage.setBuchungMoeglich(false);
                    anfrage.setBemerkung(meldung);
                    antwortliste.add(anfrage);
                }
            } else {
                String meldung = "Es ist in keinem Restaurant ein Tisch angelegt, der für die angefragte Personenzahl geeignet ist.";
                System.out.println(meldung);
                anfrage.setBuchungMoeglich(false);
                anfrage.setBemerkung(meldung);
                antwortliste.add(anfrage);
            }
        } else {
            Anfrage antwort = step2(anfrage);
            antwortliste.add(antwort);
        }
        return antwortliste;
    }

    //    vorherige Version der Anfrage mit bekannter Restaurant-ID (aus step1 wurde step2, ansonsten identisch belassen)
    public Anfrage step2(Anfrage anfrage) {
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
                    anfrage.setBuchungMoeglich(true);
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
            //in der Antwort, die über die API retouniert wird,  bleibt id_table auf 0 und buchungMöglich auch auf false
        }
        System.out.println("Die zurückgegebene Anfrage schaut so aus:");
        System.out.print("Im Restaurant ID " + anfrage.getId_restaurant() + " kann für " + anfrage.getPersonenzahl() + " Personen  am " + ldtAnfrage.format(dtf) + " Uhr der Tisch mit der ID " + anfrage.getId_table());
        if (!anfrage.isBuchungMoeglich()) System.out.print(" nicht");
        System.out.println(" gebucht werden, Bemerkung: " + anfrage.getBemerkung());
        return anfrage;
    }
}