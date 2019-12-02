package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String kitchen;
    private String street;
    private String apNr;
    private String zip;
    private String city;
    private String info;

    //ein Restaurant hat  viele Tische  --> List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", targetEntity = RestaurantTable.class)
    private List<RestaurantTable> restaurantTables;

    //ein Restaurant kann in mehrere Bookings involviert sein --> List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", targetEntity = Booking.class)
    private List<Booking> bookings;

    //leerer Konstruktor (erforderlich)
    public Restaurant() {
    }

    //Konstruktor für OpenTableCloneApplication erforderlich
    public Restaurant(String name, String kitchen, String street, String apNr, String zip, String city, String info) {
        this.name = name;
        this.kitchen = kitchen;
        this.street = street;
        this.apNr = apNr;
        this.zip = zip;
        this.city = city;
        this.info = info;
    }

    public Restaurant(int id, String name, String kitchen, String street, String apNr, String zip, String city, String info, List<RestaurantTable> restaurantTables, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.kitchen = kitchen;
        this.street = street;
        this.apNr = apNr;
        this.zip = zip;
        this.city = city;
        this.info = info;
        this.restaurantTables = restaurantTables;
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApNr() {
        return apNr;
    }

    public void setApNr(String apNr) {
        this.apNr = apNr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(List<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}