package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.servicearea.ServiceArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {
//    Optional<ServiceArea> findBy_ServiceArea_id(Long id);
//    Void deleteByArea_id(Long id);
}
