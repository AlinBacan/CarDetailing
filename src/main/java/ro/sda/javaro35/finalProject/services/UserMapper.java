package ro.sda.javaro35.finalProject.services;

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
        userForm.setUserId(entity.getUserId());
        userForm.setFirstName(entity.getFirstName());
        userForm.setLastName(entity.getLastName());
        return userForm;
    }

    @Override
    public User convertToEntity(UserDto dto) {
        User user;
        if (dto.getUserId() != null) { // din baza de date aducem o entitate sa lucram cu ea
            user = userRepository.findById(dto.getUserId()).orElse(new User());
        } else { // altfel se va creea alta
            user = new User();
        }
        user.setUserId(dto.getUserId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }
}
