package ro.sda.javaro35.finalProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;
import ro.sda.javaro35.finalProject.services.OrderLineService;
import ro.sda.javaro35.finalProject.services.OrderService;

import java.util.List;


@Controller
@RequestMapping("/service")
public class OrderController {
    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    //@Autowired
    private final OrderService orderService;
    private final OrderLineService orderLineService;
    private final CosmeticServiceService cosmeticServiceService;

    public OrderController(OrderService orderService, OrderLineService orderLineService, CosmeticServiceService cosmeticServiceService) {
        this.orderService = orderService;
        this.orderLineService = orderLineService;
        this.cosmeticServiceService = cosmeticServiceService;
    }

    @GetMapping("")
    public String showOrder(Model model) {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        model.addAttribute("services", orderDtos);
        return "services";
        // vezi .html si daca mai trebuie alte pagini
    }

}
