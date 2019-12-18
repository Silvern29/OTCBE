package otc.be.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import otc.be.config.ReviewRatingKey;

import javax.persistence.*;
import java.time.LocalDate;

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
}
