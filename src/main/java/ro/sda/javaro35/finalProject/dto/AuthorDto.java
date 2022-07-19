package ro.sda.javaro35.finalProject.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;

}
