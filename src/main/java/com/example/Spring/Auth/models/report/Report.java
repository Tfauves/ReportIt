package com.example.Spring.Auth.models.report;

import com.example.Spring.Auth.models.profile.Profile;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("report")
    @JsonIncludeProperties("id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIncludeProperties("id")
    private ServiceArea serviceArea;

    private String statusState;

    private String issueType;
    private String location;
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();

    private String adminComment;

    public Report() {}

    public Report(Profile profile, ServiceArea serviceArea, String adminComment, String issueType, String location, String description, String statusState) {
        this.profile = profile;
        this.serviceArea = serviceArea;
        this.adminComment = adminComment;
        this.issueType = issueType;
        this.location = location;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.statusState = statusState;
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

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getStatusState() {
        return statusState;
    }

    public void setStatusState(String statusState) {
        this.statusState = statusState;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
        return timestamp.format(formatter);
    }
}
