package com.tecso.exam.service;

import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.UserDTO;

public interface TokenService {
    String generateToken(User user);

    UserDTO parseToken(String token);
}