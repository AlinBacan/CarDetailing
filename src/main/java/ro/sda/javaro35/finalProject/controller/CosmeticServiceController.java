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


    @GetMapping("/books")
    public String showBooks(Model model) {
        List<CosmeticServiceDto> books = cosmeticServiceService.getAllCosmeticServices();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/create")
    public String showForm(Model model) {
        model.addAttribute("bookForm", new CosmeticServiceDto());
        return "book_create";
    }

    @PostMapping("/books/create")
    public String createBook(@ModelAttribute("bookForm") @Valid CosmeticServiceDto form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "book_create";
        }
        cosmeticServiceService.createCosmeticService(form);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{bookId}")
    public String showEditForm(@PathVariable("bookId") int id, Model model) {//Model e modelul din Spring MVC
        CosmeticServiceDto bookForm = cosmeticServiceService.findById(id);
        model.addAttribute("bookForm", bookForm);
        return "book_create";
    }
    @GetMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id, Model model) {//Model e modelul din Spring MVC
       cosmeticServiceService.deleteById(id);
        return "redirect:/books";
    }
}
