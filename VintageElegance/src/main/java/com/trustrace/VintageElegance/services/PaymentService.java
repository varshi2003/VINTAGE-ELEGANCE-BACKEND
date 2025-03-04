package com.trustrace.VintageElegance.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    public String createOrder(double amount) {
        try {
            RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject options = new JSONObject();
            options.put("amount", (int) (amount * 100)); // Razorpay uses paise (1 INR = 100 paise)
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            options.put("payment_capture", 1);

            Order order = razorpay.Orders.create(options);
            return order.toString(); // Returns order details (id, amount, currency)
        } catch (RazorpayException e) {
            throw new RuntimeException("Razorpay order creation failed: " + e.getMessage());
        }
    }
}
