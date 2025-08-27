package se.systementor.unittester.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.systementor.unittester.data.User;
import se.systementor.unittester.data.UserRepository;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRegistrationServiceTest {
    UserRegistrationService sut;
    private UserRepository userRepositoryMock;
    private EmailService emailServiceMock;

    @BeforeEach
    void setUp() {
        emailServiceMock = Mockito.mock(EmailService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        sut = new UserRegistrationService(emailServiceMock, userRepositoryMock);
    }

    @Test
    void registerUserWillFailWhenAlreadyRegistered() {
        // ARRANGE
        String email = "stefan@hej.se";
        String password = "password";
        when(userRepositoryMock.findByEmail(email)).thenReturn(new se.systementor.unittester.data.User());
        // ACT
        boolean result = sut.registerUser(email, password);
        // ASSERT
        assertFalse(result);
    }

    @Test
    void registerUserWillFailWhenWrongDomain() {
        //ARRANGE
        String email = "stefan@error.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        //ACT
        boolean result = sut.registerUser(email, "password");
        //ASSERT
        assertFalse(result);
    }

    @Test
    void registerUserWillFailWhenMoreThan10AlreadyHasBeenDoneToday() {
        //ASSERT
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(userRepositoryMock.countByRegisteredAtBetween(Mockito.any(java.sql.Timestamp.class),
                Mockito.any(java.sql.Timestamp.class))).thenReturn(10L);
        //ACT
        boolean result = sut.registerUser(email, "password");
        //ASSERT
        assertFalse(result);
    }


    @Test
    void registerUserShouldSendEmail() { // should call function in emailservice
        //ARRANGE
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(userRepositoryMock.countByRegisteredAtBetween(
                Mockito.any(java.sql.Timestamp.class),
                Mockito.any(java.sql.Timestamp.class))
        ).thenReturn(1L);
        //ACT
        boolean result = sut.registerUser(email, "password");
        //ASSERT
        assertTrue(result);
        Mockito.verify(emailServiceMock).sendWelcomeEmail(email);
    }



    @Test
    void registerUserShouldSave() { // should call function in emailservice
        //ARRANGE
        String email = "stefan@hej.se";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(userRepositoryMock.countByRegisteredAtBetween(
                Mockito.any(java.sql.Timestamp.class),
                Mockito.any(java.sql.Timestamp.class))
        ).thenReturn(1L);
        //ACT
        boolean result = sut.registerUser(email, "password");
        //ASSERT
        assertTrue(result);
        Mockito.verify(userRepositoryMock).save(Mockito.any(User.class));
    }


}