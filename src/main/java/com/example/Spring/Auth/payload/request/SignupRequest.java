package com.example.Spring.Auth.payload.request;

import java.util.Set;

public class SignupRequest {
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String zip;
    private Set<String> roles;


    public SignupRequest(String fname, String lname, String username, String password, String zip, Set<String> roles) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.zip = zip;
        this.roles = roles;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
