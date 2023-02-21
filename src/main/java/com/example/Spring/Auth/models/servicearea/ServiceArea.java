package com.example.Spring.Auth.models.servicearea;

import com.example.Spring.Auth.models.profile.ServiceAreaAdmin;
import com.example.Spring.Auth.models.report.Report;
import javax.persistence.*;
import java.util.Set;

@Entity
public class ServiceArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private ServiceAreaAdmin serviceAreaAdmin;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Report> reports;

    private String name;
    private String state;
    private String county;
    private String zipcode;
    private String longitude;
    private String latitude;
    private Integer openReports = 0;
    private Integer closedReports = 0;

    public ServiceArea() {
    }

    public ServiceArea(String name, String state, String county, String zipcode, String longitude, String latitude, Integer openReports, Integer closedReports) {
        this.name = name;
        this.state = state;
        this.county = county;
        this.zipcode = zipcode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.openReports = openReports;
        this.closedReports = closedReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getOpenReports() {
        return openReports;
    }

    public void setOpenReports(Integer openReports) {
        this.openReports = openReports;
    }

    public Integer getClosedReports() {
        return closedReports;
    }

    public void setClosedReports(Integer closedReports) {
        this.closedReports = closedReports;
    }

    public ServiceAreaAdmin getServiceAreaAdmin() {
        return serviceAreaAdmin;
    }

    public void setServiceAreaAdmin(ServiceAreaAdmin serviceAreaAdmin) {
        this.serviceAreaAdmin = serviceAreaAdmin;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
}
