package com.example.Spring.Auth.models.report;


import com.example.Spring.Auth.models.profile.Profile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    private String issue;
    private String location;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public Report() {}

    public Report(Profile profile, String issue, String location, Timestamp timestamp) {
        this.profile = profile;
        this.issue = issue;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
