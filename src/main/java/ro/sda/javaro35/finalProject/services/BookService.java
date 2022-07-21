package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.BookDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
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
        CosmeticService cosmeticService= bookMapper.convertToEntity(form);
        cosmeticService=bookRepository.save(cosmeticService);
        return bookMapper.convertToDto(cosmeticService);
    }

    public BookDto findById(long id) {
        CosmeticService entityCosmeticService= bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        return bookMapper.convertToDto(entityCosmeticService);
    }

    public void deleteById(long id) {
        bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        bookRepository.deleteById(id);
    }
}
