package com.example.Spring.Auth.controllers;

import com.example.Spring.Auth.models.auth.ERole;
import com.example.Spring.Auth.models.auth.Role;
import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.example.Spring.Auth.payload.request.LoginRequest;
import com.example.Spring.Auth.payload.request.SignupRequest;
import com.example.Spring.Auth.payload.response.JwtResponse;
import com.example.Spring.Auth.payload.response.MessageResponse;
import com.example.Spring.Auth.repositories.RoleRepository;
import com.example.Spring.Auth.repositories.ServiceAreaRepository;
import com.example.Spring.Auth.repositories.UserRepository;
import com.example.Spring.Auth.security.jwt.JwtUtils;
import com.example.Spring.Auth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ServiceAreaRepository serviceAreaRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse( jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email already used plz login or reset password"));
        }
        User user = new User(signupRequest.getFname(), signupRequest.getLname(), signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()), signupRequest.getZip());
        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        // TODO: 3/2/2023 if no service area matches zip or zip is null return message to user to contact their local govnt
        ServiceArea serviceArea = serviceAreaRepository.findByZipcode(signupRequest.getZip());
        user.setServiceArea(serviceArea);

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(()-> new RuntimeException(("error")));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MOD).orElseThrow(()-> new RuntimeException(("error")));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()-> new RuntimeException(("error")));
                        roles.add(userRole);
                        break;

                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity(new MessageResponse("User Reg success"), HttpStatus.CREATED);
    }

    @PostMapping("/areaAdminLog")
    public ResponseEntity<?> authenticateAdmin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse( jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

}
