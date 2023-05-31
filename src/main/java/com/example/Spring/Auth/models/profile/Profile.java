package com.example.Spring.Auth.models.profile;

import com.example.Spring.Auth.models.Avatar;
import com.example.Spring.Auth.models.auth.User;
import com.example.Spring.Auth.models.report.Report;
import com.example.Spring.Auth.models.servicearea.ServiceArea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String profileUsername;

    private String title = "newbie";

    private Integer civicWins = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("id")
    private Avatar profilePic;

    @OneToOne
    @JsonIncludeProperties({"id", "name", "state", "zipcode", "openReports", "closedReports"})
    private ServiceArea serviceArea;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "location", "description", "timestamp", "active", "issueType"})
    @OrderBy("timestamp DESC")
    private Set<Report> report;

    public Profile() {}

    public Profile(User user, String profileUsername, Avatar profilePic) {
        this.user = user;
        this.profileUsername = profileUsername;
        this.profilePic = profilePic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProfileUsername() {
        return profileUsername;
    }

    public void setProfileUsername(String profileUsername) {
        this.profileUsername = profileUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Avatar getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Avatar profilePic) {
        this.profilePic = profilePic;
    }

    public Set<Report> getReport() {
        return report;
    }

    public void setReport(Set<Report> report) {
        this.report = report;
    }

    public Integer getCivicWins() {
        return civicWins;
    }

    public void setCivicWins(Integer civicWins) {
        this.civicWins += civicWins;
    }

    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }
}