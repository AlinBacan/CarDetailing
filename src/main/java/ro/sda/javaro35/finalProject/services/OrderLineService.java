package ro.sda.javaro35.finalProject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.OrderLineDto;
import ro.sda.javaro35.finalProject.entities.OrderLine;
import ro.sda.javaro35.finalProject.repository.OrderLineRepository;

import javax.transaction.Transactional;


@Service
public class OrderLineService {
    private static final Logger log = LoggerFactory.getLogger(OrderLineService.class);

    private final OrderLineRepository orderLineRepository;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    // create
    public void save(OrderLine orderLine) {
        log.info("saving orderline {}", orderLine.getId());
        orderLineRepository.save(orderLine);
    }

    // findById
    public OrderLine findById(Integer id) {
        log.info("finding by id");
        return orderLineRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderLine not found"));
    }

    // update
    public void update(Integer orderLineId, OrderLineDto orderLineDto) {
        log.info("update OrderLine {}", orderLineDto.getCosmeticService());
        orderLineRepository.findById(orderLineId).map(existingOrderLine -> updateEntity(orderLineDto, existingOrderLine))
                .map(updatedOrderLine -> orderLineRepository.save(updatedOrderLine))
                .orElseThrow(() -> new RuntimeException("OrderLine not found"));
    }

    private OrderLine updateEntity(OrderLineDto orderLineDto, OrderLine existingOrderLine) {
        existingOrderLine.setCosmeticService(orderLineDto.getCosmeticService());
        existingOrderLine.setQuantity(orderLineDto.getQuantity());
        return existingOrderLine;
    }

    @Transactional
    public void delete(Integer id) {
        log.info("deleting by id");
        orderLineRepository.deleteById(id);
    }

}
