package com.example.Spring.Auth.models.profile;


import com.example.Spring.Auth.models.servicearea.ServiceArea;

import javax.persistence.*;

@Entity
public class ServiceAreaAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String jobtitle;
    private String email;
    private String phone;
    private String municipalityname;
    private String populationsize;
    private String state;

    @OneToOne
    @JoinColumn(name = "service_area_id", referencedColumnName = "id")
    private ServiceArea serviceArea;

    public ServiceAreaAdmin() {}

    public ServiceAreaAdmin(Long id, ServiceArea serviceArea) {
        this.id = id;
        this.serviceArea = serviceArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
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
}
