package otc.be.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Anfrage  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_restaurant;
    private int id_user;
    private Date datum;
    private Time uhrzeit;
    private int personenzahl;
    private int id_table;
    private boolean buchungMöglich;
    private String bemerkung;

    public Anfrage() {
    }

    public Anfrage(int id, int id_restaurant, int id_user, Date datum, Time uhrzeit, int personenzahl, int id_table, boolean buchungMöglich, String bemerkung) {
        this.id = id;
        this.id_restaurant = id_restaurant;
        this.id_user = id_user;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.personenzahl = personenzahl;
        this.id_table = id_table;
        this.buchungMöglich = buchungMöglich;
        this.bemerkung = bemerkung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Time uhrzeit) {
        this.uhrzeit = uhrzeit;
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

    public boolean isBuchungMöglich() {
        return buchungMöglich;
    }

    public void setBuchungMöglich(boolean buchungMöglich) {
        this.buchungMöglich = buchungMöglich;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
}