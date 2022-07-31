package ro.sda.javaro35.finalProject.mapper;

import org.springframework.stereotype.Service;

import ro.sda.javaro35.finalProject.dto.UserDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.repository.UserRepository;

@Service
public class UserMapper implements Mapper<User, UserDto> {

    private final UserRepository userRepository;

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto convertToDto(User entity) {
        UserDto userForm = new UserDto();
        userForm.setId(entity.getId());
        userForm.setFirstName(entity.getFirstName());
        userForm.setLastName(entity.getLastName());
        //userForm.setRole(entity.getRole());
        return userForm;
    }

    @Override
    public User convertToEntity(UserDto dto) {
        User user;
        if (dto.getId() != null) { // din baza de date aducem o entitate sa lucram cu ea
            user = userRepository.findById(dto.getId()).orElse(new User());
        } else { // altfel se va creea alta
            user = new User();
        }
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        //user.setRole(dto.getRole());

        return user;
    }
}
