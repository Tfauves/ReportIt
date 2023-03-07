package com.example.Spring.Auth.models.profile;

import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ServiceAreaAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobtitle;
    private String email;
    private String phone;
    private String municipalityname;
    private String populationsize;
    private String state;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("serviceAreaAdmin")
    private ServiceArea serviceArea;

    public ServiceAreaAdmin() {}

    public ServiceAreaAdmin(User user, String jobtitle, String email, String phone, String municipalityname, String populationsize, String state) {
        this.user = user;
        this.jobtitle = jobtitle;
        this.email = email;
        this.phone = phone;
        this.municipalityname = municipalityname;
        this.populationsize = populationsize;
        this.state = state;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
