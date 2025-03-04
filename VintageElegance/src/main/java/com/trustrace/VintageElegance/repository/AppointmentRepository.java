package com.trustrace.VintageElegance.repository;

import com.trustrace.VintageElegance.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByOutlet(String outlet);
}

