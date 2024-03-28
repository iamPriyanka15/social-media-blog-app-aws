package com.priyanka.learning.socialmediablogapp.repository;

import com.priyanka.learning.socialmediablogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
