package ro.sda.javaro35.finalProject.dto;

import lombok.Data;
import ro.sda.javaro35.finalProject.entities.CarBodyStyle;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.entities.Order;

@Data
public class OrderLineDto {
    private Integer id;
    private CosmeticService cosmeticService;
    private Long quantity;
    private CarBodyStyle carBodyStyle;
    private Order order;
}
