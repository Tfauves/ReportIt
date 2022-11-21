package com.example.Spring.Auth.models.profile;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.report.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String city;
    private String state;
    // TODO: 5/21/2022 state_fullname is returning null 
    private String state_fullname;

    private String zipcode;

    // TODO: 5/20/2022  report is showing as null need to check db 
    @OneToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Profile() {}

    public Profile(User user, String fname, String lname, String zipcode ) {
        this.user = user;
        this.fname = fname;
        this.lname = lname;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_fullname() {
        return state_fullname;
    }

    public void setState_fullname(String state_fullname) {
        this.state_fullname = state_fullname;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}