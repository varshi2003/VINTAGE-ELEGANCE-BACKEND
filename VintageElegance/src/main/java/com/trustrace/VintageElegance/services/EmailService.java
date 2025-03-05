
package com.trustrace.VintageElegance.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendReminderEmail(String toEmail, String userName, String appointmentTime) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Appointment Reminder - Vintage Elegance Salon");
            message.setText("Dear " + userName + ",\n\nThis is a reminder for your salon appointment scheduled at " + appointmentTime +
                    ". Please arrive on time.\n\nThank you!\nVintage Elegance Salon");

            mailSender.send(message);
            logger.info("Reminder email sent to {}", toEmail);
        } catch (Exception e) {
            logger.error("Failed to send reminder email to {}", toEmail, e);
        }
    }

    public void sendAppointmentConfirmation(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
            logger.info("Confirmation email sent to {}", toEmail);
        } catch (MessagingException e) {
            logger.error("Failed to send confirmation email to {}", toEmail, e);
        }
    }
}
