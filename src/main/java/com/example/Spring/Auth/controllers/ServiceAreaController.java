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

    @PostMapping("/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceArea> createServiceArea(@RequestBody ServiceArea newServiceArea, @PathVariable Long adminId) {

        ServiceAreaAdmin areaAdmin = serviceAreaAdminRepository.findById(adminId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        newServiceArea.setServiceAreaAdmin(areaAdmin);

        return new ResponseEntity<>(repository.save(newServiceArea), HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceArea> createAServiceArea(@RequestBody ServiceArea newArea) {
        return new ResponseEntity<>(repository.save(newArea), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public @ResponseBody
    List<ServiceArea> getAllServiceArea() {
        return repository.findAll();
    }

    @GetMapping("/{zip}")
    public @ResponseBody ServiceArea getAreaByZip(@PathVariable String zip) {
        return repository.findByZipcode(zip);

    }

    @PutMapping("/add/admin/{adminId}/{areaId}")
    public ResponseEntity<ServiceArea> addAdmin(@PathVariable Long adminId, @PathVariable Long areaId) {
        ServiceArea updateArea = repository.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updateArea.setServiceAreaAdmin(serviceAreaAdminRepository.getById(adminId));

        return new ResponseEntity<>(repository.save(updateArea), HttpStatus.CREATED);
    }

}
