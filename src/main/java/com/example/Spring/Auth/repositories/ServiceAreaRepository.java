package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {
//    Optional<ServiceArea> findBy_ServiceArea_id(Long id);
//    Void deleteByArea_id(Long id);
@Query(value = "SELECT * FROM service_area WHERE zipcode = ?1", nativeQuery = true)
ServiceArea findByZipcode(String zipcode);
}
