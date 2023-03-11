package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.profile.ServiceAreaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceAreaAdminRepository extends JpaRepository<ServiceAreaAdmin, Long> {
    Optional<ServiceAreaAdmin> findByUserId(Long id);
//    Void deleteByServiceAreaAdmin_id(Long id);
}
