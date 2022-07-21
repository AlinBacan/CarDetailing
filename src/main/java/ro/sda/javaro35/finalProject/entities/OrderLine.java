package ro.sda.javaro35.finalProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany
    private List<CosmeticService> cosmeticService;
    //private Integer idPrice;
    private Long quantity;
    private CarBodyStyle carBodyStyle;

    @ManyToOne
    private Order order;


}
