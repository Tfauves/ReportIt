package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.profile.ServiceAreaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAreaAdminRepository extends JpaRepository<ServiceAreaAdmin, Long> {
//    Optional<ServicaAreaAdmin> findByServiceAreaAdmin_id(Long id);
//    Void deleteByServiceAreaAdmin_id(Long id);
}
