package com.tecso.exam.service;

import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.TokenDTO;
import com.tecso.exam.domain.dto.UserLoginDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(String id);
    User save(User user);
    User update(String id, User user);
    void removeById(String id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    TokenDTO login(UserLoginDTO userLogin);
}
