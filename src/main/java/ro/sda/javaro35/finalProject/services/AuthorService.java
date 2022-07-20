package ro.sda.javaro35.finalProject.services;

import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.AuthorDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<User> findAll() {
        return authorRepository.findAll();
    }

    public void createAuthor(AuthorDto form) {
        User user = authorMapper.convertToEntity(form);
        authorRepository.save(user);
    }

    public AuthorDto findById(long id) {
        User entityUser = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        return authorMapper.convertToDto(entityUser);
    }

    public void deleteById(long id) {
        authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        authorRepository.deleteById(id);
    }
}
