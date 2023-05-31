package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.models.report.Status;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.example.Spring.Auth.repositories.ProfileRepository;
import com.example.Spring.Auth.repositories.ReportRepository;
import com.example.Spring.Auth.repositories.ServiceAreaRepository;
import com.example.Spring.Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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

        newReport.setServiceArea(serviceArea);
        newReport.setProfile(userProfile);

        newReport.setStatus(new Status());
        newReport.getStatus().setActive(true);

        serviceArea.getReports().add(newReport);
        serviceArea.setOpenReports(serviceArea.getOpenReports() + 1);

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

//    @PostMapping("/pending/{reportId}")
////    @PreAuthorize("hasRole('MOD')")
//    public @ResponseBody Report updateToPending(@PathVariable Long reportId) {
//        Report pendingReport = repository.findById(reportId)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        pendingReport.setPending(true);
//        pendingReport.setAdminComment("received");
//
//        return repository.save(pendingReport);
//    }

//    @PutMapping("resolve/{reportId}")
//    @PreAuthorize("hasRole('MOD')")
//    public @ResponseBody Report resolveReport(@PathVariable Long reportId) {
//        Report resolvedReport = repository.findById(reportId)
//                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        Profile reportSenderProfile = profileRepository.findByUser_id(
//                        resolvedReport.getProfile().getId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        resolvedReport.setResolved(true);
//        resolvedReport.setAdminComment("This report has been successfully closed");
//        resolvedReport.setPending(false);
//        resolvedReport.setActive(false);
//
//        reportSenderProfile.setCivicWins(1);
//        profileRepository.save(reportSenderProfile);
//
//        return repository.save(resolvedReport);
//    }
//
//    @PutMapping("unfounded/{reportId}")
//    public ResponseEntity<String> destroyReport(@PathVariable Long reportId) {
//        Report unfoundedReport = repository.findById(reportId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        unfoundedReport.setUnfounded(true);
//        unfoundedReport.setActive(false);
//        unfoundedReport.setPending(false);
//        unfoundedReport.setResolved(false);
//        unfoundedReport.setAdminComment("Unfounded");
//
//        Profile senderProfile = profileRepository.findByUser_id(unfoundedReport.getProfile().getId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        senderProfile.getReport().removeIf(report -> Objects.equals(report.getId(), reportId));
//        profileRepository.save(senderProfile);
//
//        repository.save(unfoundedReport);
//        return new ResponseEntity<>("deleted", HttpStatus.OK);
//    }

}
