package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.javaro35.finalProject.dto.UserDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.services.UserService;

@Controller
public class LoginController {


    private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    public String showRegisterPage(Model model){
        User newUser = new User();
        model.addAttribute("user",newUser);
        return "register";
    }

    @PostMapping("/register")
    public String add(@ModelAttribute UserDto userDto){
        userService.createUser(userDto);
        return "redirect:/";
    }


}
