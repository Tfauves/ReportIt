package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.requestinfo.RequestInfo;
import com.example.Spring.Auth.repositories.RequestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/info")
public class RequestInfoController {
    
    @Autowired
    RequestInfoRepository repository;

    @PostMapping
    public ResponseEntity<RequestInfo> createOne(@RequestBody RequestInfo newRequest) {
        
        return new ResponseEntity<>(repository.save(newRequest), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    RequestInfo getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<RequestInfo> getAll() {
        return repository.findAll();
    }
    
}
