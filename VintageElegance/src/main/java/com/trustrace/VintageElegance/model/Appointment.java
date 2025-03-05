
package com.trustrace.VintageElegance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.time.LocalDateTime;

@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private Service service;

    private LocalDateTime dateTime;
    private String state;
    private String city;
    private String outlet;
    private String gender;
    private List<Service> services;
    private List<String> stylists;
    private String date;
    private String time;
    private String name;
    private String email;
    private String phone;
    private String message;
    private Double totalCost;
    private String paymentStatus = "Successful";
    private boolean reminderSent = false;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }


    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    public Appointment() {}

    public Appointment(String state, String city, String outlet, String gender, List<Service> services,
                       List<String> stylists, String date, String time, String name, String email,
                       String phone, String message, Double totalCost, String paymentStatus,
                       String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
        this.state = state;
        this.city = city;
        this.outlet = outlet;
        this.gender = gender;
        this.services = services;
        this.stylists = stylists;
        this.date = date;
        this.time = time;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpaySignature = razorpaySignature;
        this.reminderSent = reminderSent;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getOutlet() { return outlet; }
    public void setOutlet(String outlet) { this.outlet = outlet; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public List<Service> getServices() { return services; }
    public void setServices(List<Service> services) { this.services = services; }
    public List<String> getStylists() { return stylists; }
    public void setStylists(List<String> stylists) { this.stylists = stylists; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getRazorpayOrderId() { return razorpayOrderId; }
    public void setRazorpayOrderId(String razorpayOrderId) { this.razorpayOrderId = razorpayOrderId; }
    public String getRazorpayPaymentId() { return razorpayPaymentId; }
    public void setRazorpayPaymentId(String razorpayPaymentId) { this.razorpayPaymentId = razorpayPaymentId; }
    public String getRazorpaySignature() { return razorpaySignature; }
    public void setRazorpaySignature(String razorpaySignature) { this.razorpaySignature = razorpaySignature; }
    public boolean isReminderSent() { return reminderSent; }  // ✅ Getter for reminderSent
    public void setReminderSent(boolean reminderSent) { this.reminderSent = reminderSent; }  // ✅ Setter for reminderSent
}
