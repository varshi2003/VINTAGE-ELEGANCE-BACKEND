package com.trustrace.VintageElegance.controller;

import com.trustrace.VintageElegance.model.AdminRequest;
import com.trustrace.VintageElegance.repository.AdminRequestRepository;
import com.trustrace.VintageElegance.services.AdminRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin-requests")
@CrossOrigin("*")
public class AdminRequestController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRequestController.class);
    @Autowired
    private AdminRequestService adminRequestService;

    @Autowired
    private AdminRequestRepository adminRequestRepository;

    @PostMapping("/submit")
    public AdminRequest submitRequest(@RequestBody AdminRequest request) {
        return adminRequestService.submitRequest(request);
    }


@GetMapping("/outlet/{outlet}")
public ResponseEntity<?> getRequestsByOutlet(@PathVariable String outlet) {
    String cleanOutlet = outlet.trim();  // Removes any newline or space
    List<AdminRequest> requests = adminRequestService.getRequestsByOutlet(cleanOutlet);
    return ResponseEntity.ok(requests);
}


    @GetMapping("/all")
    public List<AdminRequest> getAllRequests() {
        return adminRequestService.getAllRequests();
    }


@PutMapping("/update-status/{id}")
public ResponseEntity<?> updateRequestStatus(
        @PathVariable("id") String requestId,
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "message", required = false) String message) {

    logger.info("Received status update request: ID={}, Status={}, Message={}", requestId, status, message);

    if (status == null || message == null) {
        return ResponseEntity.badRequest().body("Missing required parameters: status and/or message");
    }

    Optional<AdminRequest> requestOptional = adminRequestRepository.findById(requestId);
    if (requestOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request ID not found");
    }

    AdminRequest request = requestOptional.get();
    request.setStatus(status);
    request.setAdminMessage(message);
    adminRequestRepository.save(request);

    return ResponseEntity.ok(request);
}


}
