package ro.sda.javaro35.finalProject.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.javaro35.finalProject.dto.UserDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/authors")
    public String showAuthor(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("authors", userList);
        return "authors";
    }

    @GetMapping("/authors/create")
    public String showForm(Model model) {
        model.addAttribute("authorForm", new UserDto());
        return "author_create";
    }

    @PostMapping("/authors/create")
    public String createAuthor(@ModelAttribute("authorForm") @Valid UserDto form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "author_create";
        }
        userService.createAuthor(form);
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{authorId}")
    public String showEditForm(@PathVariable("authorId") Integer id, Model model) {//Model e modelul din Spring MVC
        UserDto authorForm = userService.findById(id);
        model.addAttribute("authorForm", authorForm);
        return "author_create";
    }

    @GetMapping("/authors/delete/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") Integer id, Model model) {//Model e modelul din Spring MVC
        userService.deleteById(id);
        return "redirect:/authors";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
