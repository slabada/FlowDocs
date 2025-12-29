package com.flowdocs.service;

import com.flowdocs.domain.UserDomain;
import com.flowdocs.exception.AuthenticationException;
import com.flowdocs.model.LoginModel;
import com.flowdocs.model.RegistrationModel;
import com.flowdocs.model.UserModel;
import com.flowdocs.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegistrationModel reg) {
        Boolean existsUser = userService.existsByEmail(reg.email());
        if (existsUser) {
            throw new AuthenticationException.ConflictRegistrationException();
        }
        String hashPassword = passwordEncoder.encode(reg.password());
        UserModel userModel = new UserModel().toBuilder()
                .firstName(reg.firstName())
                .lastName(reg.lastName())
                .email(reg.email())
                .password(hashPassword)
                .role(reg.role())
                .build();
        UserDomain user = userService.createUser(userModel);
        return jwtUtil.generateToken(user.getId());
    }

    @Override
    public String login(LoginModel login) {
        UserDomain user = userService.findByEmail(login.email())
                .orElseThrow(AuthenticationException.ConflictAuthException::new);
        if (!user.getEmail().equals(login.email())) {
            throw new AuthenticationException.ConflictAuthException();
        }
        if (!passwordEncoder.matches(login.password(), user.getPassword())) {
            throw new AuthenticationException.ConflictAuthException();
        }
        return jwtUtil.generateToken(user.getId());
    }
}
