package com.example.creditanalisys.services;

import com.example.creditanalisys.model.entities.User;
import com.example.creditanalisys.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UserRepository userRepository;

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }


}
