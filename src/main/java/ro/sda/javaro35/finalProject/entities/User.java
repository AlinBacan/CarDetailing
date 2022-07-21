package ro.sda.javaro35.finalProject.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

    private String password;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Order> ordersList;


}
