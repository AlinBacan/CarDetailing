package ro.sda.javaro35.finalProject.dto;

import lombok.Data;
import ro.sda.javaro35.finalProject.entities.Role;

import javax.validation.constraints.Email;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private Role role;
    @Email
    private String email;

    private String password;


}
