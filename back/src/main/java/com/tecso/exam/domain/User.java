package com.tecso.exam.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "users")
@Table(indexes={@Index(columnList="id,name",name="index_user")})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    /** Person name. */
    private String name;
    /** Person last name. */
    private String lastname;
    /** Username. */
    private String username;
    /** Encrypted password. */
    private String password;
    /** Is admin. */
    private Boolean isAdmin = false;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isUsernameAndPasswordPresent() {
        return !StringUtils.isAnyEmpty(username, password);
    }

}
