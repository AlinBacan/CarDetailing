package ro.sda.javaro35.finalProject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Integer id;
    private Integer userId;
    private LocalDate orderDate;
}
