
package com.trustrace.VintageElegance.controller;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.trustrace.VintageElegance.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    private static final String KEY_ID = "rzp_test_ntXczTSfQC6BP2";
    private static final String KEY_SECRET = "VV3jjQqBDZw9uEBPTBlPNSar";

    @Autowired
    private EmailService emailService;


@PostMapping("/confirm-payment")
public ResponseEntity<?> confirmPayment(@RequestBody Map<String, Object> appointmentData) {
    try {
        String email = sanitizeString(appointmentData.get("email"));
        String name = sanitizeString(appointmentData.get("name"));
        String state = sanitizeString(appointmentData.get("state"));
        String city = sanitizeString(appointmentData.get("city"));
        String outlet = sanitizeString(appointmentData.get("outlet"));
        String date = sanitizeString(appointmentData.get("date"));
        String time = sanitizeString(appointmentData.get("time"));
        String message = sanitizeString(appointmentData.get("message"));

        List<String> servicesList = extractList(appointmentData.get("services"));
        List<String> stylistsList = extractList(appointmentData.get("stylists"));

        String services = String.join(", ", servicesList);
        String stylists = String.join(", ", stylistsList);

        String emailBody = "<h2>Vintage Elegance Appointment Confirmation</h2>"
                + "<p><strong>State:</strong> " + state + "</p>"
                + "<p><strong>City:</strong> " + city + "</p>"
                + "<p><strong>Outlet:</strong> " + outlet + "</p>"
                + "<p><strong>Services Selected:</strong> " + services + "</p>"
                + "<p><strong>Stylists Selected:</strong> " + stylists + "</p>"
                + "<p><strong>Name:</strong> " + name + "</p>"
                + "<p><strong>Message:</strong> " + message + "</p>"
                + "<p><strong>Date:</strong> " + date + "</p>"
                + "<p><strong>Time:</strong> " + time + "</p>"
                + "<p><strong>Thank you for booking with Vintage Elegance!</strong></p>";

        emailService.sendAppointmentConfirmation(email, "Your Vintage Elegance Appointment Confirmation", emailBody);

        return ResponseEntity.ok("Payment confirmed and email sent to " + email);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment confirmation: " + e.getMessage());
    }
}

    private List<String> extractList(Object obj) {
        if (obj instanceof List<?>) {
            return ((List<?>) obj).stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private String sanitizeString(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).replaceAll("[\\n\\r]", "").trim();
        }
        return "";
    }


    private List<String> sanitizeList(Object obj) {
        if (obj instanceof List<?>) {
            return ((List<?>) obj).stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .map(s -> s.replaceAll("[\\n\\r]", "").trim())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }



    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> requestData) {
        try {
            if (!requestData.containsKey("amount")) {
                return ResponseEntity.badRequest().body("Missing 'amount' field");
            }

            int amount = (int) requestData.get("amount");
            RazorpayClient razorpayClient = new RazorpayClient(KEY_ID, KEY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_123456");

            Order order = razorpayClient.Orders.create(orderRequest);

            return ResponseEntity.ok(order.toString());
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }
}
