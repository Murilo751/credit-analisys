package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/api/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO){
        UserDTO createUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createUser);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable @Valid Long id){
        return userService.updateUser(userDTO, id);
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable @Valid Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<UserDTO> deleteUser(Long id){
        return userService.deleteUser(id);
    }


}
