package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser_id(Long id);

    Void deleteByUser_id(Long id);
}
