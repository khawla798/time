package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test pour la méthode isUserAdult
    @Test
    public void testIsUserAdult() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setDateNaissance(new Date(System.currentTimeMillis() - (20L * 365 * 24 * 60 * 60 * 1000))); // 20 ans

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        boolean isAdult = userService.isUserAdult(userId);

        // Assert
        assertTrue(isAdult);  // L'utilisateur est censé être majeur
        verify(userRepository, times(1)).findById(userId);
    }

    // Test pour la méthode getUserFullName
    @Test
    public void testGetUserFullName() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        String fullName = userService.getUserFullName(userId);

        // Assert
        assertEquals("John Doe", fullName);
        verify(userRepository, times(1)).findById(userId);
    }

    // Test pour isUserAdult quand l'utilisateur est mineur
    @Test
    public void testIsUserAdult_WhenUserIsMinor() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setDateNaissance(new Date(System.currentTimeMillis() - (15L * 365 * 24 * 60 * 60 * 1000))); // 15 ans

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        boolean isAdult = userService.isUserAdult(userId);

        // Assert
        assertFalse(isAdult);  // L'utilisateur est censé être mineur
        verify(userRepository, times(1)).findById(userId);
    }

    // Test pour getUserFullName quand l'utilisateur n'existe pas
    @Test
    public void testGetUserFullName_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        String fullName = userService.getUserFullName(userId);

        // Assert
        assertNull(fullName);  // Si l'utilisateur n'existe pas, on attend un résultat null
        verify(userRepository, times(1)).findById(userId);
    }
}
