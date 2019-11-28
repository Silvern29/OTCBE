package otc.be.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;


}
