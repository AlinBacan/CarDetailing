package ro.sda.javaro35.finalProject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ro.sda.javaro35.finalProject.entities.Role;

@Data
public class UserDto {

    private Long userId;

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

    private String password;


}
