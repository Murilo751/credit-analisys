package com.example.creditanalisys.converter;

import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static UserDTO converterEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(null);
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setBirthday(user.getBirthday());
        return userDTO;
    }

    public User converterDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBirthday(userDTO.getBirthday());
        return user;
    }
}
