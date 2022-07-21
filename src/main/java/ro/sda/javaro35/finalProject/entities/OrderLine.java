package ro.sda.javaro35.finalProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer idOrderLine;

    @ManyToMany
    private CosmeticService cosmeticService;
    private Long idPrice;
    private Long quantity;
    private CarBodyStyle carBodyStyle;

//    private Order order; - dupa update, creat de Anca


}
