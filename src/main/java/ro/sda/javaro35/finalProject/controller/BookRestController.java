package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    private CosmeticServiceService cosmeticServiceService;
    @GetMapping
    public List<CosmeticServiceDto> getAllBooks(){
        return cosmeticServiceService.getAllCosmeticServices();

    }

    @PostMapping
    public CosmeticServiceDto createBook(@RequestBody CosmeticServiceDto cosmeticServiceDto){
        return cosmeticServiceService.createCosmeticService(cosmeticServiceDto);
    }
}
