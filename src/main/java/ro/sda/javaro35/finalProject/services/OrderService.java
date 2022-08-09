package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.entities.Order;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.mapper.OrderMapper;
import ro.sda.javaro35.finalProject.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDto> getAllOrders(){
        return orderRepository.findAll().stream().map(order -> orderMapper.convertToDto(order)).collect(Collectors.toList());
    }

    public OrderDto findById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new EntityNotFoundError(String.format("Order with %s does not exist", id)));
        return orderMapper.convertToDto(order);
    }

    public void deleteById(Integer id){
        orderRepository.findById(id).orElseThrow(()->new EntityNotFoundError(String.format("Order with %s does not exist", id)));
        orderRepository.deleteById(id);
    }
}
