package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int tableNrRest;
    private int pax;

    //ein Tisch ist genau einem Restaurant zugeordnet
    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable = false, updatable = false, nullable = false)
    private Restaurant restaurant;

    //ein Tisch kann in mehrere Bookings involviert sein
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantTable", targetEntity = Booking.class)
    private List<Booking> bookings;

    //leerer Konstruktor
    public RestaurantTable() {
    }

    //Konstruktor für OpenTableCloneApplication erforderlich
    public RestaurantTable(int tableNrRest, int pax, Restaurant restaurant) {
        this.tableNrRest = tableNrRest;
        this.pax = pax;
        this.restaurant = restaurant;
    }

    public RestaurantTable(int tableNrRest, int pax, Restaurant restaurant, List<Booking> bookings) {
        this.tableNrRest = tableNrRest;
        this.pax = pax;
        this.restaurant = restaurant;
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNrRest() {
        return tableNrRest;
    }

    public void setTableNrRest(int tableNrRest) {
        this.tableNrRest = tableNrRest;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
