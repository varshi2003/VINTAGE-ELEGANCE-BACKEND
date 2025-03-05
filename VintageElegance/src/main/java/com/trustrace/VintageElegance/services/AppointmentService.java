package com.trustrace.VintageElegance.services;

import com.trustrace.VintageElegance.model.Appointment;
import com.trustrace.VintageElegance.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentsByOutlet(String outlet) {
        return appointmentRepository.findByOutlet(outlet);
    }

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setPaymentStatus("Pending"); // Default status before payment
        return appointmentRepository.save(appointment);
    }


public List<Appointment> getAllAppointments() {
    try {
        return appointmentRepository.findAll();
    } catch (Exception e) {
        throw new RuntimeException("Error fetching appointments: " + e.getMessage());
    }
}
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment updatePaymentStatus(String id, String status) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setPaymentStatus(status);
            return appointmentRepository.save(appointment);
        }
        return null;
    }
}

