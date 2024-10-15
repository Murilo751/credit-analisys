package com.example.creditanalisys.model.dtos;

import java.time.LocalDate;

public record UserDTO(Long id, String username, String password, String phoneNumber, String email, LocalDate birthday) {
}
