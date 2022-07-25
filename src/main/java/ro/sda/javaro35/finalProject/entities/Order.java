package ro.sda.javaro35.finalProject.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLine;

}
