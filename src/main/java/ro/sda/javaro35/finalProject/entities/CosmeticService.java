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
public class CosmeticService {

    @Id
    @GeneratedValue
    private Integer id;
    private String serviceName;
    private Float price;

    @OneToMany(mappedBy = "cosmeticService")
    private List<OrderLine> orderLine;

}
