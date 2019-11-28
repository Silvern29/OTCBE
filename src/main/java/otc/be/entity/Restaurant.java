package otc.be.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<ResTable> resTables;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Booking> bookings;
}
