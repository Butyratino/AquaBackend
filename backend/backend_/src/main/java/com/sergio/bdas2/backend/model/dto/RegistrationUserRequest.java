package com.sergio.bdas2.backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationUserRequest {
    private String username;
    private String password;
    private String role;
}
