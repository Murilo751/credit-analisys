package com.example.creditanalisys.services;

import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.model.entities.User;
import com.example.creditanalisys.repositories.UserRepository;
import com.example.creditanalisys.converter.UserConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userConverter.converterDTOToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserConverter.converterEntityToDTO(savedUser);
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User userUpdated = userOptional.get();
            userUpdated.setUsername(userDTO.getUsername());
            userUpdated.setPassword(userDTO.getPassword());
            userUpdated.setPhone(userDTO.getPhone());
            userUpdated.setEmail(userDTO.getEmail());
            userUpdated.setBirthday(userDTO.getBirthday());
            User savedUser = userRepository.save(userUpdated);
            return ResponseEntity.ok(UserConverter.converterEntityToDTO(savedUser));
        }
        return ResponseEntity.notFound().build();
    }

    public Optional<UserDTO> getUserById(Long id){
        return userRepository.findById(id).map(UserConverter::converterEntityToDTO);
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserConverter::converterEntityToDTO).collect(Collectors.toList());
    }

    public ResponseEntity<UserDTO> deleteUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.delete(user);
            return ResponseEntity.ok(UserConverter.converterEntityToDTO(user));
        }
        return ResponseEntity.notFound().build();
    }
}
