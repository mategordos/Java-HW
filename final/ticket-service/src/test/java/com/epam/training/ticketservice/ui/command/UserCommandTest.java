package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserCommandTest {
    UserService userService = Mockito.mock(UserService.class);
    UserCommand underTest = new UserCommand(userService);
    private final static UserDto ADMIN = new UserDto("admin", User.Role.ADMIN);

    @Test
    void testSignOut_notLoggedIn() {
        Mockito.when(userService.logout()).thenReturn(Optional.empty());

        assertEquals("You need to login first!", underTest.signOut());
    }

    @Test
    void testSignOut_loggedIn() {
        Mockito.when(userService.logout()).thenReturn(Optional.of(ADMIN));

        assertEquals(ADMIN + " is logged out!", underTest.signOut());
    }

    @Test
    void signIn_validCredentials() {
        Mockito.when(userService.login("admin", "admin")).thenReturn(Optional.of(ADMIN));

        assertEquals("Successful login!", underTest.signIn("admin", "admin"));
    }

    @Test
    void signIn_invalidCredentials() {
        Mockito.when(userService.login("admin", "admin")).thenReturn(Optional.empty());

        assertEquals("Login failed due to incorrect credentials", underTest.signIn("admin", "admin"));
    }

    @Test
    void describeAccount_notSignedIn() {
        Mockito.when(userService.describe()).thenReturn(Optional.empty());

        assertEquals( "You are not signed in", underTest.describeAccount());
    }

    @Test
    void describeAccount_signedIn() {
        Mockito.when(userService.describe()).thenReturn(Optional.of(ADMIN));

        assertEquals("Signed in with privileged account '" + ADMIN.getUsername() + "'", underTest.describeAccount());
    }
}
