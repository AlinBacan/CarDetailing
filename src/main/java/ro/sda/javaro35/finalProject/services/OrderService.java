package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.entities.Order;
import ro.sda.javaro35.finalProject.entities.Role;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.mapper.OrderMapper;
import ro.sda.javaro35.finalProject.repository.CosmeticServiceRepository;
import ro.sda.javaro35.finalProject.repository.OrderRepository;
import ro.sda.javaro35.finalProject.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CosmeticServiceRepository cosmeticServiceRepository;
    private Map<CosmeticService, Long> cosmeticServicesMap = new HashMap<>();
    @Autowired
    UserRepository userRepository;

    public List<OrderDto> getAllOrders() {
        return getOrders().stream().map(order -> orderMapper.convertToDto(order)).collect(Collectors.toList());
    }


    /**
     * Filter orders list by logged in user
     * @return
     */
    private List<Order> getOrders(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();

        } else {
             username = principal.toString();
        }

        User user = userRepository.findByEmail(username);
        if (user==null){
            return new ArrayList<>();
        }
        if (Role.ADMIN.equals(user.getRole())){
            return orderRepository.findAll();
        }
        return orderRepository.findAllByUserId(user.getId());
    }

    public OrderDto findById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Order with %s does not exist", id)));
        return orderMapper.convertToDto(order);
    }

    public void save(OrderDto orderDto) {
        Order order = orderMapper.convertToEntity(orderDto);
        orderRepository.save(order);
    }

    public void deleteById(Integer id) {
        orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Order with %s does not exist", id)));
        orderRepository.deleteById(id);
    }

}

