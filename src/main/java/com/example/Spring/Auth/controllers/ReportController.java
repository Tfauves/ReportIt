package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.example.Spring.Auth.repositories.ProfileRepository;
import com.example.Spring.Auth.repositories.ReportRepository;
import com.example.Spring.Auth.repositories.ServiceAreaRepository;
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
    ServiceAreaRepository serviceAreaRepository;

    @Autowired
    UserService userService;

    @PostMapping("/{areaId}")
    public ResponseEntity<Report> createReport(@RequestBody Report newReport, @PathVariable Long areaId) {

        User currentUser = userService.getCurrentUser();
        if(currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Profile userProfile = profileRepository.findByUser_id(currentUser.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userProfile.getReport().add(newReport);

        ServiceArea serviceArea = serviceAreaRepository.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        serviceArea.getReports().add(newReport);
        serviceArea.setOpenReports(1);

        newReport.setServiceArea(serviceArea);
        newReport.setProfile(userProfile);

        return new ResponseEntity<>(repository.save(newReport), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody
    List<Report> getAllReports() {
        return repository.findAll();
    }


    @GetMapping("/{profId}")
    public @ResponseBody List<Report> findAllByProfile_id(@PathVariable Long profId) {
        return repository.findAllByProfile_id(profId);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody Report getReportById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
