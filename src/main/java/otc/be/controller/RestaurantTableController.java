package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.entity.RestaurantTable;
import otc.be.repository.BookingRepository;
import otc.be.repository.RestaurantTableRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class RestaurantTableController {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private BookingRepository bookingRepository;

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
        if (updatedRestaurantTable.getTableNrRest() > 0)
            updatedRestaurantTable.setTableNrRest(restaurantTable.getTableNrRest());
        if (updatedRestaurantTable.getPax() > 0) updatedRestaurantTable.setPax(restaurantTable.getPax());
        if (updatedRestaurantTable.getRestaurant().getId() != restaurantTable.getRestaurant().getId())
            updatedRestaurantTable.setRestaurant(restaurantTable.getRestaurant());
        restaurantTableRepository.save(updatedRestaurantTable);
        System.out.println("Der Tisch mit der ID " + updatedRestaurantTable.getId() + " ist auf Personenzahl: " + updatedRestaurantTable.getPax() + " Restaurant-ID" + updatedRestaurantTable.getRestaurant().getId() + " Rest.-Tisch-ID" + updatedRestaurantTable.getTableNrRest() + "geändert.");
        return updatedRestaurantTable;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der Tisch mit der ID " + id + " gelöscht worden sein.");
        restaurantTableRepository.deleteById(id);
    }

    public boolean getRestaurantTableByIdIsFree(Integer id) {
        Optional<RestaurantTable> oRT = getRestaurantTableById(id);
        boolean reservierenIstMöglich = true;
        if (oRT.isPresent()) {
            RestaurantTable currentRT = oRT.get();
            System.out.println("Der Tisch Nr. " + id + " gehört zum Restaurant ID " + currentRT.getRestaurant().getId() + ", Name " + currentRT.getRestaurant().getName() + ", dessen Tisch-Nr. ist " + currentRT.getTableNrRest() + ", Platz für " + currentRT.getPax() + " Personen.");
            Date anfrageDatum = Date.valueOf("2019-12-05");
            Time anfrageUhrzeit = Time.valueOf("00:30:00");
            int anfrageTag = anfrageDatum.getDay()+1;
            int anfrageMonat = anfrageDatum.getMonth() + 1;
            int anfrageJahr = anfrageDatum.getYear() + 1900;
            int anfrageStunde = anfrageUhrzeit.getHours();
            int anfrageMinute = anfrageUhrzeit.getMinutes();
            LocalDateTime ldtAnfrage = LocalDateTime.of(anfrageJahr, anfrageMonat, anfrageTag, anfrageStunde, anfrageMinute);
            LocalDateTime ldt1HourBefore = ldtAnfrage.minusMinutes(59);
            LocalDateTime ldt1HourLater = ldtAnfrage.plusMinutes(59);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            System.out.println("Zum Test wird angefragt für den " + ldtAnfrage.format(dtf) + ", zerlegt in " + anfrageTag + "." + anfrageMonat + "." + anfrageJahr + "; Uhrzeit: " + anfrageUhrzeit + ", zerlegt in " + anfrageStunde + ":" + anfrageMinute);
            //System.out.println("Es darf keine Buchung im Zeitraum von " + ldt1HourBefore.format(dtf) + " bis " + ldt1HourLater.format(dtf) + " geben.");
            System.out.println("Das heißt, das Datum aus der Buchung muss kleiner als " + ldt1HourBefore.format(dtf) + " und größer als " + ldt1HourLater.format(dtf) + " sein, damit eine Reservierung möglich ist.");

            LinkedList<Booking> bookings = bookingRepository.findRestTableByOrderByIdAsc(id);

            if (bookings.size() != 0) {
                for (int i = 0; i < bookings.size(); i++) {
                    Date bookedDate = bookings.get(i).getDate();
                    int bookedDay = bookedDate.getDay()+1;
                    int bookedMonth = bookedDate.getMonth() + 1;
                    int bookedYear = bookedDate.getYear() + 1900;
                    Time bookedTime = bookings.get(i).getTime();
                    int bookedHour = bookedTime.getHours();
                    int bookedMinute = bookedTime.getMinutes();
                    LocalDateTime ldtBooked = LocalDateTime.of(bookedYear, bookedMonth, bookedDay, bookedHour, bookedMinute);
                    System.out.print("gebucht: " + bookedDate + " um " + bookedTime + " Uhr. ");
//                    System.out.println("Der erste Weg/Ansatz:");
//                    if ((bookedYear == anfrageJahr) && (bookedMonth == anfrageMonat) && (bookedDay == anfrageTag)) {
//                        System.out.println("  --> Der Tisch wurde für den gleichen Tag bereits gebucht!");
//                        if ((bookedHour == anfrageStunde) && (bookedMinute == anfrageMinute)) {
//                            System.out.println("        --> Der Tisch wurde genau für die gleiche Uhrzeit gebucht --> keine Buchung möglich.");
//                            reservierenIstMöglich = false;
//                        } else {
//                            System.out.println("Hier müssen wir uns noch etwas für den Zeitraum von Buchungsüberschneidungen einfallen lassen. 59 min vor dem Buchungstermin bis 59 min nach dem Buchungstermin sind keine Reservierungen möglich");
//                        }
//                    }
//                    System.out.println("Der zweite Weg/Ansatz:");
                    if (ldtBooked.isBefore(ldt1HourBefore)) {
                        System.out.println("Das Buchungsdatum liegt vor dem angefragten Zeitraum (ldt1HourBefore), Buchung wäre möglich");
                    } else {
                        if (ldtBooked.isAfter(ldt1HourLater)) {
                            System.out.println("Das Buchungsdatum liegt nach dem angefragten Zeitraum (ldt1HourLater), Buchung wäre möglich");
                        } else {
                            System.out.println("Das Buchungsdatum liegt im angefragten Zeitraum, Buchung ist nicht möglich!");
                            reservierenIstMöglich = false;
                            i = bookings.size();
                        }
                    }
                } // ende for
            } //ende if
            else {
                System.out.println("Es gibt keine Einträge in Bookings - alle Termine sind frei verfügbar. ");
            }
        } else {
            System.out.println("Es gibt keinen Tisch mit der ID " + id + ".");
            reservierenIstMöglich = false;
        }
        return reservierenIstMöglich;
    }
}