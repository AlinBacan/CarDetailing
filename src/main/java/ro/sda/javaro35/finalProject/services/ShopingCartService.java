package ro.sda.javaro35.finalProject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.repository.CosmeticServiceRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class ShopingCartService {

    private Map<CosmeticService, Long> cosmeticServicesMap = new HashMap<>();
    @Autowired
    CosmeticServiceRepository cosmeticServiceRepository;


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
//        for (Map.Entry<CosmeticService, Long> entry : cosmeticServicesMap.entrySet()) {
//            Integer productKey = entry.getKey().getId();
//            cosmeticService = cosmeticServiceRepository.findById(productKey).orElse(new CosmeticService());
//
//        }
        //1.Cureti cosul
        //2. Creezi plata
        //3. Trimiti email pentru factura si servicii
    }

    public double getTotal(){
        return cosmeticServicesMap.entrySet().stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice()*entry.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).doubleValue();
    }

}
