package otc.be.config;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewRatingKey implements Serializable {

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "booking_id")
    private int bookingId;

    public ReviewRatingKey() {
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewRatingKey)) return false;
        ReviewRatingKey that = (ReviewRatingKey) o;
        return getRestaurantId() == that.getRestaurantId() &&
                getUserId() == that.getUserId() &&
                getBookingId() == that.getBookingId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRestaurantId(), getUserId(), getBookingId());
    }
}
