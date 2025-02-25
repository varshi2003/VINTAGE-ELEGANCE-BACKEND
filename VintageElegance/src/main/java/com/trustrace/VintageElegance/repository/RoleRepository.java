package com.trustrace.VintageElegance.repository;

import java.util.Optional;

import com.trustrace.VintageElegance.model.ERole;
import com.trustrace.VintageElegance.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}