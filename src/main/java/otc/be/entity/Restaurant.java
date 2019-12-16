package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import otc.be.dto.Picture;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "kitchen")
    private String kitchen;
    @Column(name = "street")
    private String street;
    @Column(name = "ap_nr")
    private String apNr;
    @Column(name = "zip")
    private String zip;
    @Column(name = "city")
    private String city;
    @Column(name = "info")
    private String info;

    //ein Restaurant hat viele Tische  --> List
    @JsonBackReference(value = "table-restaurant")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant", targetEntity = RestaurantTable.class)
    @Column(name = "restaurant_tables")
    private List<RestaurantTable> restaurantTables;

    //ein Restaurant kann in mehrere Bookings involviert sein --> List
    @JsonBackReference(value = "booking-restaurant")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant", targetEntity = Booking.class)
    @Column(name = "bookings")
    private List<Booking> bookings;

    @Column(name = "pictures")
    private String pictures;

    @Column(name = "opening_hours")
    private String openingHours;

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

    public List<Picture> getPictures() {
        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<ArrayList<Picture>>() {
        }.getType();
        return gson.fromJson(this.pictures, listOfMyClassObject);
    }

    public void setPictures(List<Picture> pictures) {
        Gson gson = new Gson();
        this.pictures = gson.toJson(pictures);
    }

    public List<OpeningTime> getOpeningHours() {
        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<ArrayList<OpeningTime>>() {
        }.getType();
        return gson.fromJson(this.openingHours, listOfMyClassObject);
    }

    public void setOpeningHours(List<OpeningTime> openingHours) {
        Gson gson = new Gson();
        this.openingHours = gson.toJson(openingHours);
    }
}