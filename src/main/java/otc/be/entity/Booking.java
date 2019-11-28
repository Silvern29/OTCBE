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
    @JoinColumn(name = "resTable")
    private ResTable resTable;

    private Date date;
}
