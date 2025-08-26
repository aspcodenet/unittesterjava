package se.systementor.unittester.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendWelcomeEmail(String email) {
        // Simulate sending a welcome email
        System.out.println("Welcome email sent to " + email);
    }
}
