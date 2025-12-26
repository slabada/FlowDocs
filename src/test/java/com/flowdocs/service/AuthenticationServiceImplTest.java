package com.flowdocs.service;

import com.flowdocs.model.LoginModel;
import com.flowdocs.model.RegistrationModel;
import com.flowdocs.model.UserModel;
import com.flowdocs.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @Mock
    private UserService userService;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void register() {
        var reg = new RegistrationModel(
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                "role"
        );
        var user = new UserModel().toBuilder()
                .id(1L)
                .firstName(reg.firstName())
                .lastName(reg.lastName())
                .email(reg.email())
                .password("hashPassword")
                .role(reg.role())
                .build();

        when(userService.existsByEmail(reg.email())).thenReturn(Boolean.FALSE);
        when(passwordEncoder.encode(reg.password())).thenReturn("hashPassword");
        when(userService.createUser(any(UserModel.class))).thenReturn(user);
        when(jwtUtil.generateToken(user.getId())).thenReturn("token");

        String token = authenticationService.register(reg);

        assertEquals("token", token);
        verify(userService).existsByEmail(reg.email());
        verify(passwordEncoder).encode(reg.password());
        verify(userService).createUser(any(UserModel.class));
        verify(jwtUtil).generateToken(user.getId());
    }

    @Test
    void login() {
        var login = new LoginModel("testEmail", "hashPassword");
        var user = new UserModel().toBuilder()
                .id(1L)
                .email(login.email())
                .password(login.password())
                .build();

        when(userService.findByEmail(login.email())).thenReturn(user);
        when(passwordEncoder.matches(login.password(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(user.getId())).thenReturn("token");

        String token = authenticationService.login(login);

        assertEquals("token", token);
        verify(userService).findByEmail(login.email());
        verify(passwordEncoder).matches(login.password(), user.getPassword());
        verify(jwtUtil).generateToken(user.getId());
    }
}