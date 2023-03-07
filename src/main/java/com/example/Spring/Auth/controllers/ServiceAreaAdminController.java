package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.ServiceAreaAdmin;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.example.Spring.Auth.repositories.ServiceAreaAdminRepository;
import com.example.Spring.Auth.repositories.ServiceAreaRepository;
import com.example.Spring.Auth.repositories.UserRepository;
import com.example.Spring.Auth.services.UserService;
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
    UserRepository userRepository;

    @Autowired
    ServiceAreaRepository serviceAreaRepository;

    @Autowired
    UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<ServiceAreaAdmin> createServiceAreaAdmin(@RequestBody ServiceAreaAdmin newAdmin, @PathVariable Long userId) {
        User adminUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newAdmin.setUser(adminUser);
        return new ResponseEntity<>(repository.save(newAdmin), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<ServiceAreaAdmin> readAllAdmin() {
        return repository.findAll();
    }

    @PutMapping("/{proId}/{areaId}")
    public @ResponseBody ServiceAreaAdmin updateServiceAreaAdminWithArea(@PathVariable Long proId, @PathVariable Long areaId) {
        ServiceAreaAdmin updateAdmin = repository.findById(proId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ServiceArea serviceArea = serviceAreaRepository.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updateAdmin.setServiceArea(serviceArea);

        return repository.save(updateAdmin);
    }
}
