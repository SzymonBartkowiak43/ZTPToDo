package com.example.ztptodo.domain.user;



import com.example.ztptodo.domain.user.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUserWithDefaultRole(UserDto userRegistrationDto) {
        String passwordHash = passwordEncoder.encode(userRegistrationDto.getPassword());
        User user = new User(userRegistrationDto.getUserName(), passwordHash);
        return userRepository.save(user);
    }

    public Optional<UserDto> findCredentialsByName(String name) {
        return userRepository.findUserByUserName(name)
                .map(UserMapper::map);
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}
