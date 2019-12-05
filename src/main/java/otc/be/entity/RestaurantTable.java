package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id") //ersetzt JsonManagedReference
public class RestaurantTable  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "table_nr_rest")
    private int tableNrRest;
    @Column(name = "pax")
    private int pax;

    //ein Tisch ist genau einem Restaurant zugeordnet
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    //@JsonManagedReference(value = "table-restaurant")
    //, insertable = false, updatable = false, nullable = false) auskommentiert, wenn es drin ist, geht das Anlegen der Tische mit der Restaurant-ID nicht
    private Restaurant restaurant;

    //ein Tisch kann in mehrere Bookings involviert sein
    @JsonBackReference(value = "booking-table")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurantTable", targetEntity = Booking.class)
    @Column(name = "bookings")
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