package otc.be.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "tabl")
    private Tabl tabl;

    private Date date;

    public Booking() {
    }

    public Booking(User user, Restaurant restaurant, Tabl tabl, Date date) {
        this.user = user;
        this.restaurant = restaurant;
        this.tabl = tabl;
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

    public Tabl getTabl() {
        return tabl;
    }

    public void setTabl(Tabl tabl) {
        this.tabl = tabl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
