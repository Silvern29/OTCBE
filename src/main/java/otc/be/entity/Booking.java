package otc.be.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
//import java.sql.Date;
//import java.sql.Time;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//ersetzt JsonManagedReference
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int pax;

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

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm") //als Anotation für Date - so einfach kanns gehen
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

//    @Column(name = "date")
//    private Date date;
//    @Column(name = "time")
//    private Time time;

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

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}