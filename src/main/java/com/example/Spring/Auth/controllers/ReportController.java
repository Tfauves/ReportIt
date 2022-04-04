package com.example.Spring.Auth.controllers;


import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.repositories.ProfileRepository;
import com.example.Spring.Auth.repositories.ReportRepository;
import com.example.Spring.Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    ReportRepository repository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report newReport) {

        User currentUser = userService.getCurrentUser();
        if(currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        newReport.setProfile(profileRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return new ResponseEntity<>(repository.save(newReport), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody
    List<Report> getAllReports() {
        return repository.findAll();
    }

    @GetMapping("{profId}")
    public List<Report> findAllByProfile_id(@PathVariable Long profId) {
        return repository.findAllByProfile_id(profId);
    }
}
