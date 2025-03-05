package com.trustrace.VintageElegance.repository;

import com.trustrace.VintageElegance.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByOutlet(String outlet);

    List<Appointment> findByDateTimeBetweenAndReminderSentFalse(LocalDateTime start, LocalDateTime end);
}

