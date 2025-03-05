package com.trustrace.VintageElegance.services;

import com.trustrace.VintageElegance.model.AdminRequest;
import com.trustrace.VintageElegance.repository.AdminRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AdminRequestService {
    private static final Logger logger = Logger.getLogger(AdminRequestService.class.getName());
    @Autowired
    private AdminRequestRepository adminRequestRepository;

    public AdminRequest submitRequest(AdminRequest request) {
        request.setStatus("PENDING");
        return adminRequestRepository.save(request);
    }

    public List<AdminRequest> getRequestsByOutlet(String outlet) {
        return adminRequestRepository.findByOutlet(outlet);
    }


    public List<AdminRequest> getAllRequests() {
        return adminRequestRepository.findAllByStatus("PENDING");
    }

    public AdminRequest updateRequestStatus(String id, String status, String message) {
        Optional<AdminRequest> optionalRequest = adminRequestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            AdminRequest request = optionalRequest.get();
            request.setStatus(status);
            request.setAdminMessage(message);
            return adminRequestRepository.save(request);
        }
        return null;
    }
}

