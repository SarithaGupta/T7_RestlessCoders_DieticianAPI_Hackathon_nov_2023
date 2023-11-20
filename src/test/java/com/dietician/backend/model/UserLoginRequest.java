package com.dietician.backend.model;

import lombok.Data;

@Data
public class UserLoginRequest {
    String userLoginEmail;
    String password;
}
