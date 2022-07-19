package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.BookDto;
import ro.sda.javaro35.finalProject.entities.Book;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public List<BookDto> getAllBooks() {

        return bookRepository.findAll().stream().map(book -> bookMapper.convertToDto(book)).collect(Collectors.toList());
    }

    public BookDto createBook(BookDto form) {
        Book book = bookMapper.convertToEntity(form);
        book=bookRepository.save(book);
        return bookMapper.convertToDto(book);
    }

    public BookDto findById(long id) {
        Book entityBook = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        return bookMapper.convertToDto(entityBook);
    }

    public void deleteById(long id) {
        bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        bookRepository.deleteById(id);
    }
}
