package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.auth.ERole;
import com.example.Spring.Auth.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
