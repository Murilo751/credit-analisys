package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.model.entities.User;
import com.example.creditanalisys.repositories.UserRepository;
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
        User user = userConverter.converterDTO(userDTO);
        User savedUser = userRepository.save(user);
        return UserConverter.converterEntity(savedUser);
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
            return ResponseEntity.ok(UserConverter.converterEntity(savedUser));
        }
        return ResponseEntity.notFound().build();
    }

    public UserDTO getUserById(Long id){
        return DozerConverter.parseObject(userRepository.findById(id), UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return DozerConverter.parseListObjects(users);
    }

    public ResponseEntity<UserDTO> deleteUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.delete(user);
            return ResponseEntity.ok(DozerConverter.parseObject(user, UserDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
