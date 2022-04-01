package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.repositories.ProfileRepository;
import com.example.Spring.Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    ProfileRepository repository;

    @Autowired
    UserService userService;


   @GetMapping
    public @ResponseBody List<Profile> getAll() {
       return repository.findAll();
   }

   @PostMapping
    public ResponseEntity<Profile> createOne(@RequestBody Profile newProfile) {
       User currentUser = userService.getCurrentUser();

       if (currentUser == null) {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
       newProfile.setUser(currentUser);

       return new ResponseEntity<>(repository.save(newProfile), HttpStatus.CREATED);
   }







}
