package com.example.Spring.Auth.repositories;

import com.example.Spring.Auth.models.requestinfo.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestInfoRepository extends JpaRepository<RequestInfo, Long> {
}
