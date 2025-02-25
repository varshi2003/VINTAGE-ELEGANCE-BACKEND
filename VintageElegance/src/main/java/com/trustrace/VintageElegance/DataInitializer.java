package com.trustrace.VintageElegance;

import com.trustrace.VintageElegance.model.ERole;
import com.trustrace.VintageElegance.model.Role;
import com.trustrace.VintageElegance.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
            roleRepository.save(new Role(ERole.ROLE_USER));
        }
        if (!roleRepository.findByName(ERole.ROLE_MODERATOR).isPresent()) {
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        }
        if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }
    }
}
