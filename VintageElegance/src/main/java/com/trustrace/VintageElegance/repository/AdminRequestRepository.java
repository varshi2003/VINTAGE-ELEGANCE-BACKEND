package com.trustrace.VintageElegance.repository;

import com.trustrace.VintageElegance.model.AdminRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AdminRequestRepository extends MongoRepository<AdminRequest, String> {
    List<AdminRequest> findByOutlet(String outlet);
}