package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
