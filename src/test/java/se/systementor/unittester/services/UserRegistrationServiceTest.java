package se.systementor.unittester.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.systementor.unittester.data.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRegistrationServiceTest {

    private UserRegistrationService userRegistrationService;
    private UserRepository userRepositoryMock;
    private EmailService emailServiceMock;

    @BeforeEach
    void setUp() {
        emailServiceMock = Mockito.mock(EmailService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userRegistrationService = new UserRegistrationService(emailServiceMock, userRepositoryMock);
    }

    @Test
    void registerUserWillFailWhenAlreadyRegistered() {
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(new se.systementor.unittester.data.User());
        //when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        boolean result = userRegistrationService.registerUser(email, "password");
        assertFalse(result);
    }

    @Test
    void registerUserWillFailWhenWrongDomain() {
        String email = "stefan@error.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        boolean result = userRegistrationService.registerUser(email, "password");
        assertFalse(result);
    }

    @Test
    void registerUserWillFailWhenMoreThan10AlreadyHasBeenDoneToday() {
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(userRepositoryMock.countByRegisteredAtBetween(
            Mockito.any(java.sql.Timestamp.class),
            Mockito.any(java.sql.Timestamp.class))
        ).thenReturn(10L);
        boolean result = userRegistrationService.registerUser(email, "password");
        assertFalse(result);
    }


    @Test
    void registerUserSendEmail() {
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(userRepositoryMock.countByRegisteredAtBetween(
                Mockito.any(java.sql.Timestamp.class),
                Mockito.any(java.sql.Timestamp.class))
        ).thenReturn(1L);
        boolean result = userRegistrationService.registerUser(email, "password");
        assertTrue(result);
        Mockito.verify(emailServiceMock).sendWelcomeEmail(email);
    }


}
