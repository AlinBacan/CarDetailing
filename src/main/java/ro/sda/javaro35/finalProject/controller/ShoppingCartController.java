package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;
import ro.sda.javaro35.finalProject.services.ShopingCartService;

@Controller
public class ShoppingCartController {

    @Autowired
    private CosmeticServiceService cosmeticServiceService;
    @Autowired
    private ShopingCartService shopingCartService;

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {

        return showCart(model);
    }

    private String showCart (Model model) {
        model.addAttribute("cosmeticServicesInCart", shopingCartService.getServicesInCart());
        model.addAttribute("total", shopingCartService.getTotal());
        return "shoppingCart";
    }


    @GetMapping("/shoppingCart/addService/{id}")
    public String addToCart(@PathVariable("id") Integer id, @RequestParam("quantity") Long quantity, Model model) {

        CosmeticServiceDto cosmeticServiceDto = cosmeticServiceService.findById(id);
        shopingCartService.addService(cosmeticServiceDto, quantity);
        return showCart(model);
    }



    @GetMapping("/shoppingCart/removeService/{id}")
    public String removeFromCart(@PathVariable("id") Integer id, Model model) {


        // to remove from cart a service before checkout
        CosmeticServiceDto cosmeticServiceDto = cosmeticServiceService.findById(id);
        shopingCartService.removeService(cosmeticServiceDto);
        return showCart(model);
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        shopingCartService.checkout();
        return shoppingCart(model);
        //to redirect to other page
    }
}
