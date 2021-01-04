package com.tecso.exam.controller;


import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.TokenDTO;
import com.tecso.exam.domain.dto.UserLoginDTO;
import com.tecso.exam.domain.dto.UserRegisterDTO;
import com.tecso.exam.service.TokenService;
import com.tecso.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by efalcon
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;
    private TokenService tokenService;
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserController(UserService userService, TokenService tokenService, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void singUpUser(@RequestBody UserRegisterDTO user) {
        this.userService.save(convertToEntity(user));
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody UserLoginDTO userLogin) {
        return this.userService.login(userLogin);
    }

    @GetMapping("/checkUsernameAvailability/{username}")
    public boolean checkUserAvailability(@PathVariable String username) {
        return !this.userService.existsByUsername(username);
    }

    private User convertToEntity(UserRegisterDTO userRegister) {
        return modelMapper.map(userRegister, User.class);
    }
}
