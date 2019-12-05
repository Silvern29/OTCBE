package otc.be.dto;

import java.sql.Date;

public class BookingDTO {
    private AuthorizedUserDTO userDTO;
    private int restaurantId;
    private int tableId;
    private Date date;

    public AuthorizedUserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(AuthorizedUserDTO userDTO) {
        this.userDTO = userDTO;
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
}
