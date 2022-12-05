package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.profile.ServiceAreaAdmin;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.example.Spring.Auth.repositories.ServiceAreaAdminRepository;
import com.example.Spring.Auth.repositories.ServiceAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/areaAdmin")
public class ServiceAreaAdminController {
    @Autowired
    ServiceAreaAdminRepository repository;

    @Autowired
    ServiceAreaRepository serviceAreaRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceAreaAdmin> createServiceAreaAdmin(@RequestBody ServiceAreaAdmin newAdmin) {
        return new ResponseEntity<>(repository.save(newAdmin), HttpStatus.CREATED);
    }

    @PostMapping("/{areaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceAreaAdmin> createServiceAreaAdminWithArea(@RequestBody ServiceAreaAdmin newAdmin, @PathVariable Long areaId) {
        ServiceArea serviceArea = serviceAreaRepository.getById(areaId);
        serviceArea.setServiceAreaAdmin(newAdmin);
        serviceAreaRepository.save(serviceArea);


        return new ResponseEntity<>(repository.save(newAdmin), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<ServiceAreaAdmin> readAllAdmin() {
        return repository.findAll();
    }
}
