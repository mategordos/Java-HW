package com.epam.training.ticketservice.core.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Availability;

class UserServiceImplTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService underTest = new UserServiceImpl(userRepository);

    @Test
    void testLoginShouldSetLoggedInUserWhenUsernameAndPasswordAreCorrect() {
        // Given
        User user = new User("user", "password", User.Role.USER);
        Optional<User> expected = Optional.of(user);
        when(userRepository.findByUsernameAndPassword("user", "pass")).thenReturn(Optional.of(user));

        // When
        Optional<UserDto> actual = underTest.login("user", "pass");

        // Then
        assertEquals(expected.get().getUsername(), actual.get().getUsername());
        assertEquals(expected.get().getRole(), actual.get().getRole());
        verify(userRepository).findByUsernameAndPassword("user", "pass");
    }

    @Test
    void testLoginShouldReturnOptionalEmptyWhenUsernameOrPasswordAreNotCorrect() {
        // Given
        Optional<UserDto> expected = Optional.empty();
        when(userRepository.findByUsernameAndPassword("dummy", "dummy")).thenReturn(Optional.empty());

        // When
        Optional<UserDto> actual = underTest.login("dummy", "dummy");

        // Then
        assertEquals(expected, actual);
        verify(userRepository).findByUsernameAndPassword("dummy", "dummy");
    }

    @Test
    void testLogoutShouldReturnOptionalEmptyWhenThereIsNoOneLoggedIn() {
        // Given
        Optional<UserDto> expected = Optional.empty();

        // When
        Optional<UserDto> actual = underTest.logout();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testLogoutShouldReturnThePreviouslyLoggedInUserWhenThereIsALoggedInUser() {
        // Given
        User user = new User("user", "password", User.Role.USER);
        when(userRepository.findByUsernameAndPassword("user", "pass")).thenReturn(Optional.of(user));
        Optional<UserDto> expected = underTest.login("user", "password");

        // When
        Optional<UserDto> actual = underTest.logout();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testDescribeShouldReturnTheLoggedInUserWhenThereIsALoggedInUser() {
        // Given
        User user = new User("user", "password", User.Role.USER);
        when(userRepository.findByUsernameAndPassword("user", "pass")).thenReturn(Optional.of(user));
        Optional<UserDto> expected = underTest.login("user", "password");

        // When
        Optional<UserDto> actual = underTest.describe();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testDescribeShouldReturnOptionalEmptyWhenThereIsNoOneLoggedIn() {
        // Given
        Optional<UserDto> expected = Optional.empty();

        // When
        Optional<UserDto> actual = underTest.describe();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testRegisterUserShouldCallUserRepositoryWhenTheInputIsValid() {
        // Given
        // When
        underTest.registerUser("user", "pass");

        // Then
        verify(userRepository).save(any());
    }

    @Test
    void testIsAvailable_available() {
        TestUserServiceImpl underTest2 = new TestUserServiceImpl(userRepository);
        underTest2.setLoggedInUser(new UserDto("admin", User.Role.ADMIN));

        assertEquals(Availability.available().isAvailable(), underTest2.isAvailable().isAvailable());
    }

    @Test
    void testIsAvailable_unavailable() {
        TestUserServiceImpl underTest2 = new TestUserServiceImpl(userRepository);
        underTest2.setLoggedInUser(null);

        assertEquals(Availability.unavailable("reason").isAvailable(), underTest2.isAvailable().isAvailable());
    }

    @Test
    void testIsAvailable_unavailableBecauseOfRole() {
        TestUserServiceImpl underTest2 = new TestUserServiceImpl(userRepository);
        underTest2.setLoggedInUser(new UserDto("user", User.Role.USER));

        assertEquals(Availability.unavailable("reason").isAvailable(), underTest2.isAvailable().isAvailable());
    }

    private class TestUserServiceImpl extends UserServiceImpl {
        private UserDto loggedInUser;

        public TestUserServiceImpl(UserRepository userRepository) {
            super(userRepository);
        }

        @Override public Optional<UserDto> describe() {
            return Optional.ofNullable(loggedInUser);
        }

        public void setLoggedInUser(UserDto loggedInUser) {
            this.loggedInUser = loggedInUser;
        }
    }
}
