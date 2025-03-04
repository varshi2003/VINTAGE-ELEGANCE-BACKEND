package com.trustrace.VintageElegance.model;
import org.springframework.data.mongodb.core.mapping.Field;

public class Service {
    @Field("name")
    private String name;

    @Field("cost")
    private double cost;

    @Field("gender")
    private String gender;

    public Service() {}

    public Service(String name, double cost, String gender) {
        this.name = name;
        this.cost = cost;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
