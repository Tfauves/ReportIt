package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.Avatar;
import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.payload.api.response.AddressInfo;
import com.example.Spring.Auth.repositories.AvatarRepository;
import com.example.Spring.Auth.repositories.ProfileRepository;
import com.example.Spring.Auth.repositories.ReportRepository;
import com.example.Spring.Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    ProfileRepository repository;

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${Spring-Auth.app.addressApiKey}")
    private String apiKey;


//////////////Admin only routes
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody Profile updateProfileById(@PathVariable Long id, @RequestBody Profile updateData) {
        Profile updatedProfile = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return repository.save(updatedProfile);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {
       return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> destroyProfile(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    List<Profile> ReadAllProfiles() {
        return repository.findAll();
    }


/////////////Open routes
   @PostMapping("/pic")
    public ResponseEntity<Profile> createOne(@RequestBody Profile newProfile) {
       User currentUser = userService.getCurrentUser();
       if (currentUser == null) {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }

       Avatar avatar = newProfile.getProfilePic();
       avatar.setUrl(newProfile.getProfilePic().getUrl());
       avatarRepository.save(avatar);

       newProfile.setUser(currentUser);
       return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
   }

   @PostMapping
   public ResponseEntity<Profile> createNewProfile(@RequestBody Profile newProfile) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        newProfile.setUser(currentUser);
        return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
   }

   @GetMapping("/address")
   public ResponseEntity<?> getAddressInfo() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Profile profile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String uri = "https://service.zipapi.us/zipcode/" + currentUser.getZip() + "/?X-API-KEY=" + apiKey;

       AddressInfo response = restTemplate.getForObject(uri, AddressInfo.class);
       return ResponseEntity.ok(response);
   }

    @GetMapping("/self")
    public @ResponseBody Profile getSelf() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return null;
        }

        return repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public @ResponseBody Profile getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    // TODO: 1/27/2023 need new profile update path 
    @PutMapping
    public @ResponseBody Profile updateProfileById(@RequestBody Profile updateData) {
       User currentUser = userService.getCurrentUser();
       if(currentUser == null) {
           return null;
       }

       Profile updatedProfile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       return repository.save(updatedProfile);
   }

}
