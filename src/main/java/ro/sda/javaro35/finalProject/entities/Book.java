package ro.sda.javaro35.finalProject.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data

public class Book {

    @Id
    @GeneratedValue
    private Long id;


 private String title;
 private String author;

}
