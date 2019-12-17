package otc.be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class BookingDTO {
    private int id;
    private int pax = -1;
    private int userId = -1;
    private String jws;
    private int restaurantId = -1;
    private int tableId = -1;
    private LocalDateTime localDateTime;

    public BookingDTO() {
    }

    public BookingDTO(int pax, int userId, String jws, int restaurantId, int tableId) {
        this.pax = pax;
        this.userId = userId;
        this.jws = jws;
        this.restaurantId = restaurantId;
        this.tableId = tableId;
    }

    public BookingDTO(int id, int pax, int userId, String jws, int restaurantId, int tableId) {
        this.id = id;
        this.pax = pax;
        this.userId = userId;
        this.jws = jws;
        this.restaurantId = restaurantId;
        this.tableId = tableId;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
