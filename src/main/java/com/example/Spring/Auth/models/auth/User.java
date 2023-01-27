package com.example.Spring.Auth.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @JsonIgnore
    private String fname;

    @NotBlank
    @JsonIgnore
    private String lname;

    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = 5, max = 100)
    @JsonIgnore
    private String password;

    @NotBlank
    private String zip;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String fname, String lname, String username, String password, String zip, Set<Role> roles) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.zip = zip;
        this.roles = roles;
    }

    public User(String username, String encode) {
    }

    public User(String fname, String lname, String username, String encode, String zip) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = encode;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}