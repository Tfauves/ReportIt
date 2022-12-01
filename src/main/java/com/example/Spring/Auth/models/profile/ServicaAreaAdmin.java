package com.example.Spring.Auth.models.profile;


import com.example.Spring.Auth.models.servicearea.ServiceArea;

import javax.persistence.*;

@Entity
public class ServicaAreaAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_area_id", referencedColumnName = "id")
    private ServiceArea serviceArea;

    public ServicaAreaAdmin() {}

    public ServicaAreaAdmin(Long id, ServiceArea serviceArea) {
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
}
