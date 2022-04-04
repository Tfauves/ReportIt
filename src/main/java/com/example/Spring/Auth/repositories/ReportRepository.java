package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT * FROM report WHERE profile_id = ?1", nativeQuery = true)
    List<Report> findAllByProfile_id(Long id);
}
