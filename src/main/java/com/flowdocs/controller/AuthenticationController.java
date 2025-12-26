package com.flowdocs.controller;

import com.flowDocs.api.AuthenticationApi;
import com.flowDocs.model.AuthenticationResponse;
import com.flowDocs.model.LoginRequest;
import com.flowDocs.model.RegistrationRequest;
import com.flowdocs.model.LoginModel;
import com.flowdocs.model.RegistrationModel;
import com.flowdocs.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<AuthenticationResponse> registration(RegistrationRequest request) {
        var reg = new RegistrationModel(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        String jwt = authenticationService.register(reg);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AuthenticationResponse().jwt(jwt));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest request) {
        LoginModel login = new LoginModel(
                request.getEmail(),
                request.getPassword()
        );
        String jwt = authenticationService.login(login);
        return ResponseEntity.ok(new AuthenticationResponse().jwt(jwt));
    }
}
