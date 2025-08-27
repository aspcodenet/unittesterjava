package se.systementor.unittester.services;

import org.springframework.stereotype.Service;
import se.systementor.unittester.data.User;
import se.systementor.unittester.data.UserRepository;

@Service
public class UserRegistrationService {
    private final EmailService emailService;
    private final UserRepository userRepository;

    public UserRegistrationService(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public boolean registerUser(String email, String password) {
        // Simulate user registration logic
        if(isAlreadyRegistered(email)) {
            return false; // User already registered
        }
        if(!correctDomain(email)) {
            return false;
        }
        if(registrationsToday() >= 10) {
            return false; // Daily registration limit reached
        }
        saveUserToDatabase(email, password);
        sendWelcomeEmail(email);
        return true; // Registration successful
    }

    private void saveUserToDatabase(String email, String password) {
//       User user = new User();
//       user.setEmail(email);
//       user.setPasswordHashed(password);
//       user.setRegisteredAt(new java.sql.Timestamp(System.currentTimeMillis()));
//       userRepository.save(user);

       User user = User.builder()
               .email(email)
               .passwordHashed(password) // In real life, hash the password!
               .registeredAt(new java.sql.Timestamp(System.currentTimeMillis()))
               .build();

        userRepository.save(user);
    }

    private int registrationsToday() {
        return (int) userRepository.countByRegisteredAtBetween(
            java.sql.Timestamp.valueOf(java.time.LocalDate.now().atStartOfDay()),
            java.sql.Timestamp.valueOf(java.time.LocalDate.now().plusDays(1).atStartOfDay())
        );
    }

    private boolean correctDomain(String email) {
        return email.endsWith("@hej.se") || email.endsWith("@hej.com");
    }

    private void sendWelcomeEmail(String email) {
        emailService.sendWelcomeEmail(email);
    }

    private boolean isAlreadyRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }}
