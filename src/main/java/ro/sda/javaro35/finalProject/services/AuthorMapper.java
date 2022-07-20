package ro.sda.javaro35.finalProject.services;

import org.springframework.stereotype.Service;

import ro.sda.javaro35.finalProject.dto.AuthorDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.repository.AuthorRepository;

@Service
public class AuthorMapper implements Mapper<User, AuthorDto> {

    private final AuthorRepository authorRepository;

    public AuthorMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto convertToDto(User entity) {
        AuthorDto authorForm = new AuthorDto();
        authorForm.setId(entity.getId());
        authorForm.setFirstName(entity.getFirstName());
        authorForm.setLastName(entity.getLastName());
        return authorForm;
    }

    @Override
    public User convertToEntity(AuthorDto dto) {
        User user;
        if (dto.getId() != null) { // din baza de date aducem o entitate sa lucram cu ea
            user = authorRepository.findById(dto.getId()).orElse(new User());
        } else { // altfel se va creea alta
            user = new User();
        }
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }
}
