package otc.be.dto;

import otc.be.entity.Restaurant;

import java.util.List;

public class RestaurantDTO {
    private int id;
    private String name;
    private String kitchen;
    private String street;
    private String apNr;
    private String zip;
    private String city;
    private String info;
    private List<Picture> pictures;

    public RestaurantDTO(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.kitchen = restaurant.getKitchen();
        this.street = restaurant.getStreet();
        this.apNr = restaurant.getApNr();
        this.zip = restaurant.getZip();
        this.city = restaurant.getCity();
        this.info = restaurant.getInfo();
        this.pictures = restaurant.getPictures();
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

//    public String getPictures() {
//        Gson gson = new Gson();
//        this.pictures = gson.toJson(restaurant.getPictures());
//        return pictures;
//    }
//
//    public void setPictures(String pictures) {
//        this.pictures = pictures;
//    }
}