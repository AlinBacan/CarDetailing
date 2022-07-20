package ro.sda.javaro35.finalProject.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private String firstName;

    @Column(name="alaBala")
    private String lastName;

    private Role role;

    private String email;

    private String password;


}
