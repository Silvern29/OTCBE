package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id") //ersetzt JsonManagedReference
public class Booking  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //eine Buchung ist genau einem Kunden zugeordnet
    //@JsonManagedReference(value = "booking-user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")        //, insertable = false, updatable = false, nullable = false)
    private User user;

    // würde ich weglassen, da jeder Tabl bereits einem Restaurant zugeordnet ist
    // eine Buchung ist genau einem Restaurant zugeordnet
    //@JsonManagedReference(value = "booking-restaurant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")   //, insertable = false, updatable = false, nullable = false)
    private Restaurant restaurant;

    //eine Buchung ist genau einem Tisch zugeordnet
    //@JsonManagedReference(value = "booking-table")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant_table")   //,  insertable = false, updatable = false, nullable = false)
    private RestaurantTable restaurantTable;

    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;

    //leerer Konstruktor erforderlich
    public Booking() {
    }

    public Booking(User user, Restaurant restaurant, RestaurantTable restaurantTable, Date date) {
        this.user = user;
        this.restaurant = restaurant;
        this.restaurantTable = restaurantTable;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}