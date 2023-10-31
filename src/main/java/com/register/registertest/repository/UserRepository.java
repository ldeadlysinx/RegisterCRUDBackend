package com.register.registertest.repository;

import com.register.registertest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    public User findByName(String name);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:partialName%")
    List<User> findUsersByPartialName(String partialName);
}
