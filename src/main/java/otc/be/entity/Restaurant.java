package otc.be.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String kitchen;
    private String address;
    private String info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Tabl> tabls;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Booking> bookings;

    public Restaurant() {
    }

    public Restaurant(String name, String kitchen, String address, String info, List<Tabl> tabls, List<Booking> bookings) {
        this.name = name;
        this.kitchen = kitchen;
        this.address = address;
        this.info = info;
        this.tabls = tabls;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Tabl> getTabls() {
        return tabls;
    }

    public void setTabls(List<Tabl> tabls) {
        this.tabls = tabls;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
