package com.trustrace.VintageElegance.controller;

import com.trustrace.VintageElegance.model.Appointment;
import com.trustrace.VintageElegance.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/outlet/{outlet}")
    public ResponseEntity<List<Appointment>> getAppointmentsByOutlet(@PathVariable String outlet) {
        String cleanedOutlet = outlet.trim(); // Remove newlines and spaces
        List<Appointment> appointments = appointmentService.getAppointmentsByOutlet(cleanedOutlet);
        return ResponseEntity.ok(appointments);
    }


    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {

        return appointmentService.bookAppointment(appointment);
    }


@GetMapping("/all")
public ResponseEntity<?> getAllAppointments() {
    try {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable String id) {
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}/update-status")
    public Appointment updatePaymentStatus(@PathVariable String id, @RequestParam String status) {
        return appointmentService.updatePaymentStatus(id, status);
    }
}
