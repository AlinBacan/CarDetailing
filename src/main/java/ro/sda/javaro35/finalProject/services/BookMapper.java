package ro.sda.javaro35.finalProject.services;

import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.BookDto;
import ro.sda.javaro35.finalProject.entities.Book;

@Service
public class BookMapper implements Mapper<Book, BookDto> {
    @Override
    public BookDto convertToDto(Book entity) {
        BookDto result = new BookDto();
        result.setAuthor(entity.getAuthor());
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        return result;

    }

    @Override
    public Book convertToEntity(BookDto dto) {
        Book result = new Book();
        result.setAuthor(dto.getAuthor());
        result.setId(dto.getId());
        result.setTitle(dto.getTitle());
        return result;
    }
}
