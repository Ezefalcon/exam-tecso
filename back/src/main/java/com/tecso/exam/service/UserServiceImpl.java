package com.tecso.exam.service;

import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.TokenDTO;
import com.tecso.exam.domain.dto.UserLoginDTO;
import com.tecso.exam.repository.UserRepository;
import com.tecso.exam.service.exceptions.UsernameOrPasswordInvalidException;
import com.tecso.exam.service.exceptions.alreadyexists.UsernameAlreadyExistsException;
import com.tecso.exam.service.exceptions.notfound.UserNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Collections.emptyList;


/**
 * Created by efalcon
 */
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        if(user.isUsernameAndPasswordPresent()) {
            if(!userRepository.existsByUsername(user.getUsername())) {
                String encodedPassword = this.passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                return this.userRepository.save(user);
            }
            throw new UsernameAlreadyExistsException();
        }
        throw new UsernameOrPasswordInvalidException();
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public void removeById(String id) {
        if(this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        } else throw new UserNotFoundException();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User applicationUser = userRepository.findByUsername(username);
        if (Objects.isNull(applicationUser)) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    @Override
    public TokenDTO login(UserLoginDTO userLogin) {
        if(userLogin.fieldsPresent()) {
            User user = findByUsername(userLogin.getUsername());
            if(Objects.nonNull(user) && passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                String token = tokenService.generateToken(user);
                return new TokenDTO(token);
            }
        }
        throw new UsernameOrPasswordInvalidException();
    }
}
