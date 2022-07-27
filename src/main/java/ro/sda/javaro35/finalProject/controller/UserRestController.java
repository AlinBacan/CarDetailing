package ro.sda.javaro35.finalProject.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sda.javaro35.finalProject.dto.UserDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(UserRestController.API_USERS)

public class UserRestController {

public static final String API_USERS = "/api/users";

private final UserService userService;
@Autowired
public UserRestController(UserService restUserService){this.userService = restUserService;}

   /* @PostMapping("/")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));

    }*/




}
