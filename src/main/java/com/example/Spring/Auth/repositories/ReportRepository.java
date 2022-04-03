package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
