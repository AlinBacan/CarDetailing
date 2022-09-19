package ro.sda.javaro35.finalProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.dto.OrderDto;
import ro.sda.javaro35.finalProject.entities.CarBodyStyle;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.entities.Order;
import ro.sda.javaro35.finalProject.entities.OrderLine;
import ro.sda.javaro35.finalProject.mapper.CosmeticServiceMapper;
import ro.sda.javaro35.finalProject.mapper.OrderLineMapper;
import ro.sda.javaro35.finalProject.mapper.OrderMapper;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;
import ro.sda.javaro35.finalProject.services.OrderLineService;
import ro.sda.javaro35.finalProject.services.OrderService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderLineMapper orderLineMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CosmeticServiceMapper cosmeticServiceMapper;

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
    public String showOrders(Model model) {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        model.addAttribute("orders", orderDtos);
        return "orders";
        // vezi .html si daca mai trebuie alte pagini
    }


    @PostMapping("/add{id}")
    public String add(@PathVariable("id") Integer id, Model model) {
        // check if order exists, if not create one
        Order order = (Order) model.getAttribute("id");
        CosmeticServiceDto cosmeticServiceDto = cosmeticServiceService.findById(id);
        orderBuilder(model, order);
        OrderLine orderLine = new OrderLine(id, cosmeticServiceMapper.convertToEntity(cosmeticServiceDto), 1L, CarBodyStyle.SEDAN, order);
        orderLineService.save(orderLine);
        return "redirect:/order";
    }

    @PostMapping("/edit{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        // check if order exists, if not create one
        Order order = (Order) model.getAttribute("id");
        List<OrderLine> orderLines = new ArrayList<>();
        //orderLines.add(order.getId(),order.getOrderLine().stream().);
        order.getOrderLine().stream(); //??
        order.getOrderLine(); //??
        //orderLines.forEach(orderLine -> {orderLine.getId(),update(id, orderLineMapper.convertToDto())});
        //CosmeticServiceDto cosmeticServiceDto = cosmeticServiceService.findById(id);
        //orderBuilder(model, order);
        //OrderLine orderLine = new OrderLine(id, cosmeticServiceMapper.convertToEntity(cosmeticServiceDto), 1L, CarBodyStyle.SEDAN, order);
        //orderLineService.save(orderLine);
        return "redirect:/order";
    }

    private void orderBuilder(Model model, Order order) {
        /*if(orderDto == null){
            OrderDto newOrderDto = new OrderDto();
            orderService.save(newOrderDto);
            model.addAttribute("id",newOrderDto);
        }*/
        if (order == null) {
            Order newOrder = new Order();
            OrderDto newOrderDto = orderMapper.convertToDto(newOrder);
            orderService.save(newOrderDto);
            model.addAttribute("id", newOrder);
        }
    }

}
