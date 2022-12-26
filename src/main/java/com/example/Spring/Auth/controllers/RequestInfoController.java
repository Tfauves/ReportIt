package com.example.Spring.Auth.controllers;

// TODO: 12/26/2022 needs testing 
import com.example.Spring.Auth.models.requestinfo.RequestInfo;
import com.example.Spring.Auth.repositories.RequestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody
    RequestInfo getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    public @ResponseBody
    List<RequestInfo> getAll() {
        return repository.findAll();
    }
    
}
