package com.bns.demo;

import com.bns.demo.model.User;
import com.bns.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryCrudTests {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setRole("Admin");
        user.setStatus("Active");
        user.setEmailId("testuser@example.com");
        user.setContactNum("1234567890");
        when(userRepository.save(user)).thenReturn(user);
        User saved = userRepository.save(user);
        assertNotNull(saved);
        assertEquals("Test", saved.getFirstName());
        assertEquals("User", saved.getLastName());
        assertEquals("testuser@example.com", saved.getEmailId());
    }

    @Test
    void testReadUser() {
        User user = new User();
        user.setFirstName("Read");
        user.setLastName("User");
        user.setRole("User");
        user.setStatus("Active");
        user.setEmailId("readuser@example.com");
        user.setContactNum("9876543210");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> found = userRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Read", found.get().getFirstName());
        assertEquals("User", found.get().getLastName());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUserId(2L);
        user.setFirstName("Update");
        user.setLastName("User");
        user.setRole("User");
        user.setStatus("Active");
        user.setEmailId("updateuser@example.com");
        user.setContactNum("1112223333");

        User updatedUser = new User();
        updatedUser.setUserId(2L);
        updatedUser.setFirstName("Update");
        updatedUser.setLastName("User");
        updatedUser.setRole("User");
        updatedUser.setStatus("Inactive");
        updatedUser.setEmailId("updateuser@example.com");
        updatedUser.setContactNum("1112223333");

        when(userRepository.save(user)).thenReturn(updatedUser);
        User updated = userRepository.save(user);
        assertEquals("Inactive", updated.getStatus());
    }

    @Test
    void testDeleteUser() {
        Long id = 3L;
        doNothing().when(userRepository).deleteById(id);
        userRepository.deleteById(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}