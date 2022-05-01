package com.crickinfo.crickinfo.service.impl;

import com.crickinfo.crickinfo.dto.UserDto;
import com.crickinfo.crickinfo.entity.Role;
import com.crickinfo.crickinfo.entity.User;
import com.crickinfo.crickinfo.repository.UserRepository;
import com.crickinfo.crickinfo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getFullName(), userDto.getUserName(),
                userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
}