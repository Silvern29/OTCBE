package otc.be.entity;

import org.springframework.data.annotation.CreatedDate;
import otc.be.config.ReviewRatingKey;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *Review uses @EmbeddedId -> no auto generation of id, id has to be send on creation as ReviewRatingKey-Object
 * "id": {
 *     "restaurant_id": 45,
 *     "userId": 41
 * }
 *
 **/

@Entity
public class Review {
    @EmbeddedId
    private ReviewRatingKey id;

    @CreatedDate
    @Column(name = "date")
    private LocalDate localDate;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("restaurant_id")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @MapsId("booking_id")
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "rating")
    private int rating;

    public Review() {
    }

    public ReviewRatingKey getId() {
        return id;
    }

    public void setId(ReviewRatingKey id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
