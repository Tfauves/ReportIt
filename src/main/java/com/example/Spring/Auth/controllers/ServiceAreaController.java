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
@RequestMapping("api/area")
public class ServiceAreaController {
    @Autowired
    ServiceAreaRepository repository;
    
    @Autowired
    ServiceAreaAdminRepository serviceAreaAdminRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceArea> createServiceArea(@RequestBody ServiceArea newServiceArea) {
        return new ResponseEntity<>(repository.save(newServiceArea), HttpStatus.CREATED);
    }

    @GetMapping()
    public @ResponseBody
    List<ServiceArea> getAllServiceArea() {
        return repository.findAll();
    }

    @PutMapping()
    public @ResponseBody ServiceArea updateServiceArea(@RequestBody ServiceArea updateData) {
        return updateData;
    }

    // TODO: 12/3/2022 finish adding admin to the service area. 
    @PutMapping("/{adminId}")
    public ResponseEntity<ServiceArea> updateServiceAreaAdmin(@RequestBody ServiceArea updateData, @PathVariable Long adminId) {
        
      if (updateData.getServiceAreaAdmin() != null) ;

        return new ResponseEntity<>(repository.save(updateData), HttpStatus.CREATED);
    }
}
