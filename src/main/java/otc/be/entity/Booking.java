package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int pax;

    //eine Buchung ist genau einem Kunden zugeordnet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    // eine Buchung ist genau einem Restaurant zugeordnet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    //eine Buchung ist genau einem Tisch zugeordnet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant_table")
    private RestaurantTable restaurantTable;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    //leerer Konstruktor erforderlich
    public Booking() {
    }

    public Booking(int pax, User user, Restaurant restaurant, RestaurantTable restaurantTable, LocalDateTime localDateTime) {
        this.pax = pax;
        this.user = user;
        this.restaurant = restaurant;
        this.restaurantTable = restaurantTable;
        this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}