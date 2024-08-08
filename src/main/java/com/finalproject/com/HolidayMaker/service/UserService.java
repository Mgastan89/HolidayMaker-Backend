package com.finalproject.com.HolidayMaker.service;

import com.finalproject.com.HolidayMaker.dto.UserDTO;
import com.finalproject.com.HolidayMaker.model.User;
import com.finalproject.com.HolidayMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = toEntity(userDTO);
        return toDto(userRepository.save(user));
    }

    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return toDto(user); // Return DTO if credentials are valid
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    private UserDTO toDto(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getUserType(),
                user.getAddress()
        );
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        user.setAddress(dto.getAddress());
        return user;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id).map(this::toDto).orElse(null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = toEntity(userDTO);
        return toDto(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setUserType(userDTO.getUserType());
            user.setAddress(userDTO.getAddress());
            return toDto(userRepository.save(user));
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
