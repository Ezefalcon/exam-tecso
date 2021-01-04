package com.tecso.exam.domain.dto;

import lombok.Data;

/**
 * Created by efalcon
 */
@Data
public class UserRegisterDTO {
    private String name;
    private String lastname;
    private String username;
    private String password;
}
