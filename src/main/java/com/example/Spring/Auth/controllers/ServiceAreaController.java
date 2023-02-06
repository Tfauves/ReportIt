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
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<ServiceArea> getAllServiceArea() {
        return repository.findAll();
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public @ResponseBody ServiceArea updateServiceArea(@PathVariable Long id, @RequestBody ServiceArea updateData) {
//        ServiceArea updateArea = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        if(updateData.getServiceAreaAdmin() != null) updateArea.setServiceAreaAdmin(updateData.getServiceAreaAdmin());
//
//
//        return repository.save(updateArea);
//    }


}
