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
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<ServiceAreaAdmin> readAllAdmin() {
        return repository.findAll();
    }

    // TODO: 12/6/2022 not working 
    @PutMapping("/{id}")
    public @ResponseBody ServiceAreaAdmin updateServiceAreaAdminWithArea(@PathVariable Long id, @RequestBody ServiceAreaAdmin updateData) {
        ServiceAreaAdmin updateAdmin = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(updateData.getServiceArea() != null) updateAdmin.setServiceArea(updateAdmin.getServiceArea());

        return repository.save(updateAdmin);
    }
}
