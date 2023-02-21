package com.example.Spring.Auth.models.report;

import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Report {
    // if crash reset generation type to identity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("report")
    @JsonIncludeProperties("id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    private ServiceArea serviceArea;

    private String issueType;
    private String location;
    private String description;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private Boolean isActive = true;

    public Report() {}

    public Report(Profile profile, ServiceArea serviceArea, String issueType, String location, String description, Timestamp timestamp) {
        this.profile = profile;
        this.serviceArea = serviceArea;
        this.issueType = issueType;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


}
