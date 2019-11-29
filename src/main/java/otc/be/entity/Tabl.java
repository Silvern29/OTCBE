package otc.be.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tabl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pax;

    @ManyToOne
    @JoinColumn(name="restaurant")
    private Restaurant restaurant;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabl")
//    private List<Booking> bookings;

    public Tabl() {
    }

    public Tabl(int pax, Restaurant restaurant/*, List<Booking> bookings*/) {
        this.pax = pax;
        this.restaurant = restaurant;
//        this.bookings = bookings;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

//    public List<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(List<Booking> bookings) {
//        this.bookings = bookings;
//    }
}
