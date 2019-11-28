package otc.be.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ResTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="restaurant")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resTable")
    private List<Booking> bookings;


}
