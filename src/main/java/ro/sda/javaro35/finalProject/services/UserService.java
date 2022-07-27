package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.UserDto;
import ro.sda.javaro35.finalProject.entities.User;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.mapper.UserMapper;
import ro.sda.javaro35.finalProject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void createUser(UserDto form) {
        User user = userMapper.convertToEntity(form);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
    }

    public UserDto findById(Integer id) {
        User entityUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        return userMapper.convertToDto(entityUser);
    }

    public void deleteById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Book with %s does not exist", id)));
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("Ivalid username or password");
        }
        List roles = new ArrayList();
        String role= "ROLE_" +user.getRole();
        roles.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),roles);
    }
}
