package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.profile.ServicaAreaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceAreaAdminRepository extends JpaRepository<ServicaAreaAdmin, Long> {
//    Optional<ServicaAreaAdmin> findByServiceAreaAdmin_id(Long id);
//    Void deleteByServiceAreaAdmin_id(Long id);
}
