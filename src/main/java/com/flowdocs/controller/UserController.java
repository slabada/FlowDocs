package com.flowdocs.controller;

import com.flowDocs.api.UserApi;
import com.flowDocs.model.UserDto;
import com.flowdocs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }
}
