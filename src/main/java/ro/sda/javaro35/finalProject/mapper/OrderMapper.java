package ro.sda.javaro35.finalProject.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.entities.Order;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.mapper.Mapper;
import ro.sda.javaro35.finalProject.repository.OrderRepository;
import ro.sda.javaro35.finalProject.repository.UserRepository;

@Service
@AllArgsConstructor
public class OrderMapper implements Mapper<Order, OrderDto> {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    @Override
    public OrderDto convertToDto(Order entity) {
        OrderDto result = new OrderDto();
        result.setId(entity.getId());
        result.setOrderDate(entity.getOrderDate());
        result.setUserId(entity.getUser()!=null?entity.getUser().getId():null);
        return result;
    }

    @Override
    public Order convertToEntity(OrderDto dto) {

        Order result= new Order();
        if (dto.getId()!=null){
            result= orderRepository.findById(dto.getId()).orElse(result);
        }
        result.setId(dto.getId());
        result.setOrderDate(dto.getOrderDate());
        if(dto.getUserId()!=null){
            User user=userRepository.findById(dto.getUserId()).orElse(null);
            result.setUser(user);
        }

        return result;
    }
}
