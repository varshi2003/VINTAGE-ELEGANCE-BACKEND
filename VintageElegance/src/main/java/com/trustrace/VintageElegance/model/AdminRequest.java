package com.trustrace.VintageElegance.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "admin_requests")
public class AdminRequest {
    @Id
    private String id;
    private String moderatorId;
    private String state;
    private String city;
    private String outlet;

    private List<ServiceRequest> services;
    private List<StylistRequest> stylists;
    private List<CostUpdate> costUpdates;

    private String status; // PENDING, ACCEPTED, REJECTED
    private String adminMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public List<ServiceRequest> getServices() {
        return services;
    }

    public void setServices(List<ServiceRequest> services) {
        this.services = services;
    }

    public List<StylistRequest> getStylists() {
        return stylists;
    }

    public void setStylists(List<StylistRequest> stylists) {
        this.stylists = stylists;
    }

    public List<CostUpdate> getCostUpdates() {
        return costUpdates;
    }

    public void setCostUpdates(List<CostUpdate> costUpdates) {
        this.costUpdates = costUpdates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }
}

@Data
class ServiceRequest {
    private String serviceName;
    private double cost;
    private String gender;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

@Data
class StylistRequest {
    private String name;
    private String gender;

    public String getStylistName() {
        return name;
    }

    public void setStylistName(String stylistName) {
        this.name = stylistName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

@Data
class CostUpdate {
    private String serviceName;
    private double updatedCost;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getUpdatedCost() {
        return updatedCost;
    }

    public void setUpdatedCost(double updatedCost) {
        this.updatedCost = updatedCost;
    }
}