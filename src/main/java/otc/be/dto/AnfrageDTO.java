package otc.be.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class AnfrageDTO {
    private int id_restaurant;
    private int id_user;
    private int personenzahl;
    private int id_table;
    private boolean buchungMoeglich;
    private LocalDateTime localDateTime;

    public AnfrageDTO() {
    }

    public AnfrageDTO(int id_restaurant, int id_user, Date date, Time time, int personenzahl, int id_table, boolean buchungMoeglich) {
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.personenzahl = personenzahl;
        this.id_table = id_table;
        this.buchungMoeglich = buchungMoeglich;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPersonenzahl() {
        return personenzahl;
    }

    public void setPersonenzahl(int personenzahl) {
        this.personenzahl = personenzahl;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }

    public boolean isBuchungMoeglich() {
        return buchungMoeglich;
    }

    public void setBuchungMoeglich(boolean buchungMoeglich) {
        this.buchungMoeglich = buchungMoeglich;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}