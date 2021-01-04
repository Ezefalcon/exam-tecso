package com.tecso.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by efalcon
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String lastname;
    private String username;
    private Boolean isAdmin;
}
