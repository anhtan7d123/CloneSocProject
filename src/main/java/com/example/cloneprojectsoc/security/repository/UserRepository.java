package com.example.cloneprojectsoc.security.repository;

import com.example.cloneprojectsoc.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
