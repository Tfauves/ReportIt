package com.example.Spring.Auth.models.servicearea;

import com.example.Spring.Auth.models.profile.ServicaAreaAdmin;
import javax.persistence.*;

@Entity
public class ServiceArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_area_admin_id", referencedColumnName = "id")
    private ServicaAreaAdmin serviceAreaAdmin;

    private String name;
    private String state;
    private String county;
    private String zipCode;
    private String longitude;
    private String latitude;
    private Integer openReports = 0;
    private Integer closedReports = 0;

    public ServiceArea() {
    }

    public ServiceArea(Long id, ServicaAreaAdmin serviceAreaAdmin, String name, String state, String county, String zipCode, String longitude, String latitude, Integer openReports, Integer closedReports) {
        this.id = id;
        this.serviceAreaAdmin = serviceAreaAdmin;
        this.name = name;
        this.state = state;
        this.county = county;
        this.zipCode = zipCode;
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

    public ServicaAreaAdmin getServiceAreaAdmin() {
        return serviceAreaAdmin;
    }

    public void setServiceAreaAdmin(ServicaAreaAdmin serviceAreaAdmin) {
        this.serviceAreaAdmin = serviceAreaAdmin;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
}
