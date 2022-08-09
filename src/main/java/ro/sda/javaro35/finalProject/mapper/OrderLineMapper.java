package ro.sda.javaro35.finalProject.mapper;

import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderLineDto;
import ro.sda.javaro35.finalProject.entities.OrderLine;

@Service
public class OrderLineMapper implements Mapper<OrderLine, OrderLineDto>{
    public OrderLineDto convertToDto(OrderLine entity){
        OrderLineDto result = new OrderLineDto();
        result.setId(entity.getId());
        result.setOrder(entity.getOrder());
        result.setCarBodyStyle(entity.getCarBodyStyle());
        result.setQuantity(entity.getQuantity());
        result.setCosmeticService(entity.getCosmeticService());
        return result;
    }

    public OrderLine convertToEntity(OrderLineDto dto){
        OrderLine result = new OrderLine();
        result.setId(dto.getId());
        result.setOrder(dto.getOrder());
        result.setCarBodyStyle(dto.getCarBodyStyle());
        result.setQuantity(dto.getQuantity());
        result.setCosmeticService(dto.getCosmeticService());
        return result;
    }

}

