package com.tecso.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by efalcon
 */
@Data
@AllArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;

    public boolean fieldsPresent() {
        return !StringUtils.isAllBlank(username,password);
    }
}
