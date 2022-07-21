package ro.sda.javaro35.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sda.javaro35.finalProject.dto.BookDto;
import ro.sda.javaro35.finalProject.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    private BookService bookService;
    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();

    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }
}
