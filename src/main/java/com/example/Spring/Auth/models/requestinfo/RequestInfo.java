package com.example.Spring.Auth.models.requestinfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fname;
    String lname;
    String jobtitle;
    String email;
    String phone;
    String municipalityname;
    String populationsize;
    String state;
    String comments;

    public RequestInfo() {
    }

    public RequestInfo(Long id, String fname, String lname, String jobtitle, String email, String phone, String municipalityname, String populationsize, String state, String comments) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.jobtitle = jobtitle;
        this.email = email;
        this.phone = phone;
        this.municipalityname = municipalityname;
        this.populationsize = populationsize;
        this.state = state;
        this.comments = comments;
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

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMunicipalityname() {
        return municipalityname;
    }

    public void setMunicipalityname(String municipalityname) {
        this.municipalityname = municipalityname;
    }

    public String getPopulationsize() {
        return populationsize;
    }

    public void setPopulationsize(String populationsize) {
        this.populationsize = populationsize;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
