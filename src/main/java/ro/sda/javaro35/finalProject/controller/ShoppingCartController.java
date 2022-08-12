package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;
import ro.sda.javaro35.finalProject.services.OrderService;
import ro.sda.javaro35.finalProject.services.ShopingCartService;

@Controller
public class ShoppingCartController {

    @Autowired
    private  CosmeticServiceService cosmeticServiceService;
    @Autowired
    private ShopingCartService shopingCartService;



   @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart(){
    ModelAndView modelAndView = new ModelAndView("/shoppingCart");
    modelAndView.addObject("orders", shopingCartService.getServicesInCart());
    modelAndView.addObject("total", shopingCartService.getTotal());
    return modelAndView;
    }
}
