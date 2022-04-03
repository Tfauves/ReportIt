package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.payload.api.response.AddressInfo;
import com.example.Spring.Auth.repositories.ProfileRepository;
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

        if (updateData.getFname() != null) updatedProfile.setFname(updateData.getFname());
        if (updateData.getLname() != null) updatedProfile.setLname(updateData.getLname());
//        if (updateData.getCity() != null) updatedProfile.setCity(updateData.getCity());
//        if (updateData.getState() != null) updatedProfile.setState(updateData.getState());
        if (updateData.getZipcode() != null) updatedProfile.setZipcode(updateData.getZipcode());
        return repository.save(updatedProfile);
    }

   @GetMapping
   @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody List<Profile> getAll() {
       return repository.findAll();
   }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody Profile getOneById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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


/////////////Open routes
   @PostMapping
    public ResponseEntity<Profile> createOne(@RequestBody Profile newProfile) {
       User currentUser = userService.getCurrentUser();
       if (currentUser == null) {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }

       newProfile.setUser(currentUser);
       return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
   }

   @GetMapping("/address/{zipCode}")
   public ResponseEntity<?> getAddressInfo(@PathVariable String zipCode) {
        String uri = "https://service.zipapi.us/zipcode/" + zipCode + "/?X-API-KEY=" + apiKey;

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

   @PutMapping
    public @ResponseBody Profile updateProfileById(@RequestBody Profile updateData) {
       User currentUser = userService.getCurrentUser();
       if(currentUser == null) {
           return null;
       }

       Profile updatedProfile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       if (updateData.getFname() != null) updatedProfile.setFname(updateData.getFname());
       if (updateData.getLname() != null) updatedProfile.setLname(updateData.getLname());
//       if (updateData.getCity() != null) updatedProfile.setCity(updateData.getCity());
//       if (updateData.getState() != null) updatedProfile.setState(updateData.getState());
       if (updateData.getZipcode() != null) updatedProfile.setZipcode(updateData.getZipcode());
       return repository.save(updatedProfile);
   }


}
