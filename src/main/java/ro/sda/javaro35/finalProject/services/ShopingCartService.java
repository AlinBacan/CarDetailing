package ro.sda.javaro35.finalProject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.exceptions.IllegalQuantityException;
import ro.sda.javaro35.finalProject.repository.CosmeticServiceRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class ShopingCartService {

    private Map<CosmeticServiceDto, Long> shoppingCartProducts = new HashMap<>();
    @Autowired
    CosmeticServiceRepository cosmeticServiceRepository;

    /**
     * Add a new product to shopping cart
     * @param cosmeticService
     * @param quantity
     */
    public void addService(CosmeticServiceDto cosmeticService, Long quantity) {
        if (shoppingCartProducts.containsKey(cosmeticService)) {
            if (quantity < 0) {
                log.info("Please enter a positive number");
                throw new IllegalQuantityException("Please enter a positive number");
            }
            shoppingCartProducts.put(cosmeticService, shoppingCartProducts.get(cosmeticService) + quantity);

        } else {
            shoppingCartProducts.put(cosmeticService, quantity);
        }
    }

    public void removeService(CosmeticServiceDto cosmeticService) {
        if (shoppingCartProducts.containsKey(cosmeticService)) {
            shoppingCartProducts.remove(cosmeticService);
        }
    }

    public Map<CosmeticServiceDto, Long> getServicesInCart() {
        return Collections.unmodifiableMap(shoppingCartProducts);
    }

    // TODO link between user and his orders

    public void checkout() {


        //TODO Implement chechout logic:
            //1.Cureti cosul
            //2. Creezi plata
            //3. Trimiti email pentru factura si servicii
        shoppingCartProducts.clear();
    }

    public double getTotal(){
        return shoppingCartProducts.entrySet().stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()*entry.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).doubleValue();
    }

}
