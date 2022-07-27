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

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/user/create")
    public String showForm(Model model) {
        model.addAttribute("usersForm", new UserDto());
        return "user_create";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("usersForm") @Valid UserDto form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "user_create";
        }
        userService.createUser(form);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {//Model e modelul din Spring MVC
        UserDto userForm = userService.findById(id);
        model.addAttribute("userForm", userForm);
        return "user_create";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {//Model e modelul din Spring MVC
        userService.deleteById(id);
        return "redirect:/users";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
