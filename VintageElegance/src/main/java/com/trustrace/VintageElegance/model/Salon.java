
package com.trustrace.VintageElegance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "salons")
public class Salon {
    @Id
    private String id;
    private String state;
    private String city;
    private String outletName;
    private List<Service> services;
    private List<String> maleStylists;
    private List<String> femaleStylists;
    private String moderator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<String> getMaleStylists() {
        return maleStylists;
    }

    public void setMaleStylists(List<String> maleStylists) {
        this.maleStylists = maleStylists;
    }

    public List<String> getFemaleStylists() {
        return femaleStylists;
    }

    public void setFemaleStylists(List<String> femaleStylists) {
        this.femaleStylists = femaleStylists;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }
}
