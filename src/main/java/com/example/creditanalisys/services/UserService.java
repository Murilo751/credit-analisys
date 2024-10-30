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

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = DozerConverter.parseObject(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return DozerConverter.parseObject(savedUser, UserDTO.class);
    }

    public UserDTO getUserById(Long id){
        return DozerConverter.parseObject(userRepository.findById(id), UserDTO.class);
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id){
        User userSearch = DozerConverter.parseObject(getUserById(id), User.class);
        if (userSearch == null){
            return ResponseEntity.notFound().build();
        } else {
            userSearch.setUsername(userDTO.getUsername());
            userSearch.setPassword(userDTO.getPassword());
            userSearch.setPhone(userDTO.getPhone());
            userSearch.setEmail(userDTO.getEmail());
            userSearch.setBirthday(userDTO.getBirthday());
            User savedUser = userRepository.save(userSearch);
            return ResponseEntity.ok(DozerConverter.parseObject(savedUser, UserDTO.class));
        }
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return DozerConverter.parseListObjects(users, UserDTO.class);
    }

    public ResponseEntity<UserDTO> deleteUser(Long id){
        User userOptional = DozerConverter.parseObject(getUserById(id), User.class);
        if (userOptional != null){
            userRepository.delete(userOptional);
            return ResponseEntity.ok(DozerConverter.parseObject(userOptional, UserDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
