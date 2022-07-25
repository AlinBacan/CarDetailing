package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.services.CosmeticServiceService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CosmeticServiceController {
    @Autowired
    private CosmeticServiceService cosmeticServiceService;


    @GetMapping("/service")
    public String showCosmeticService(Model model) {
        List<CosmeticServiceDto> services = cosmeticServiceService.getAllCosmeticServices();
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/service/create")
    public String showForm(Model model) {
        model.addAttribute("form", new CosmeticServiceDto());
        return "service-add";
    }

    @PostMapping("/service/create")
    public String createCosmeticService(@ModelAttribute("form") @Valid CosmeticServiceDto form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "service-add";
        }
        cosmeticServiceService.createCosmeticService(form);
        return "redirect:/service";
    }

    @GetMapping("/service/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {//Model e modelul din Spring MVC
        CosmeticServiceDto form = cosmeticServiceService.findById(id);
        model.addAttribute("Form", form);
        return "service_create";
    }
    @GetMapping("/service/delete/{id}")
    public String deleteCosmeticService(@PathVariable("id") int id, Model model) {//Model e modelul din Spring MVC
       cosmeticServiceService.deleteById(id);
        return "redirect:/service";
    }
}
