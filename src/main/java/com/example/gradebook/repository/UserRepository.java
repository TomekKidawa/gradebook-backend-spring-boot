package com.example.gradebook.repository;

//import java.awt.print.Pageable;
import java.util.Optional;

import com.example.gradebook.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByUsernameContaining(String username, Pageable pageable);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}