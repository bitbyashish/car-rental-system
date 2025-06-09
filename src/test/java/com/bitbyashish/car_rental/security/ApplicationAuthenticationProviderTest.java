package com.bitbyashish.car_rental.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bitbyashish.car_rental.entity.User;
import com.bitbyashish.car_rental.enums.Role;
import com.bitbyashish.car_rental.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationAuthenticationProviderTest {

    @Autowired
    private ApplicationAuthenticationProvider authenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testAuthenticateSuccess() {
        // Create test user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(passwordEncoder.encode("password123"));
        user.setRole(Role.USER);
        userRepository.save(user);

        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "test@example.com",
                        "password123"));

        assertNotNull(authentication);
        assertEquals("test@example.com", authentication.getName());
    }

    @Test
    void testAuthenticateFailure() {
        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            "nonexistent@example.com",
                            "wrongpassword"));
        });
    }
}
