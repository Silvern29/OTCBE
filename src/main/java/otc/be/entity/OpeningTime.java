package otc.be.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class OpeningTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String dayOfWeek;
    private Time opening;
    private Time closing;



}
