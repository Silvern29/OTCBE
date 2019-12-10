package otc.be.dto;

import java.sql.Date;
import java.sql.Time;

public class BookingDTO {
    private int pax;
    private int userId;
    private String jws;
    private int restaurantId;
    private int tableId;
    private Date date;
    private Time time;

    public BookingDTO() {
    }

    public BookingDTO(int pax, int userId, String jws, int restaurantId, int tableId, Date date, Time time) {
        this.pax = pax;
        this.userId = userId;
        this.jws = jws;
        this.restaurantId = restaurantId;
        this.tableId = tableId;
        this.date = date;
        this.time = time;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJws() {
        return jws;
    }

    public void setJws(String jws) {
        this.jws = jws;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
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
