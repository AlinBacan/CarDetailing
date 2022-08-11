package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.entities.Order;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.mapper.OrderMapper;
import ro.sda.javaro35.finalProject.repository.CosmeticServiceRepository;
import ro.sda.javaro35.finalProject.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

// @Scope...
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CosmeticServiceRepository cosmeticServiceRepository;
    private Map<CosmeticService, Long> cosmeticServicesMap = new HashMap<>();

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> orderMapper.convertToDto(order)).collect(Collectors.toList());
    }
    // TODO get the orders made by the user with id = user_id

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

    // from example project bellow
    // methods for shopping cart
    public void addService(CosmeticService cosmeticService, Long quantity) {
        if (cosmeticServicesMap.containsKey(cosmeticService)) {
            cosmeticServicesMap.replace(cosmeticService, cosmeticServicesMap.get(cosmeticService) + quantity);
            if (quantity < 0) {
                log.info("Please enter a positive number");
            }
        } else {
            cosmeticServicesMap.put(cosmeticService, quantity);
        }
    }

    public void removeService(CosmeticService cosmeticService) {
        if (cosmeticServicesMap.containsKey(cosmeticService)) {
            cosmeticServicesMap.replace(cosmeticService, cosmeticServicesMap.get(cosmeticService) - 1);
        } else if (cosmeticServicesMap.get(cosmeticService) == 1) {
            cosmeticServicesMap.remove(cosmeticService);
        }
    }

    public Map<CosmeticService, Long> getServicesInCart() {
        return Collections.unmodifiableMap(cosmeticServicesMap);
    }

    // TODO link between user and his orders

    public void checkout() {
        CosmeticService cosmeticService;
        for (Map.Entry<CosmeticService, Long> entry : cosmeticServicesMap.entrySet()) {
            Integer productKey = entry.getKey().getId();
            // Refresh quantity for every product before checking
            cosmeticService = cosmeticServiceRepository.findById(productKey).orElseThrow();

        // to see if it is usable for services, no stock required
        }
    }

    public double getTotal(){
        return cosmeticServicesMap.entrySet().stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()*entry.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).doubleValue();
    }
}

