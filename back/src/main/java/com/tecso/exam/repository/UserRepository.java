package com.tecso.exam.repository;

import com.tecso.exam.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}