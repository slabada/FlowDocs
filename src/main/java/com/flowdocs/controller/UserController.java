package com.flowdocs.controller;

import com.flowDocs.api.UserApi;
import com.flowDocs.model.UserDto;
import com.flowdocs.domain.UserDomain;
import com.flowdocs.mapper.UserMapper;
import com.flowdocs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        UserDomain domain = userService.getUser(id);
        UserDto dto = userMapper.toDto(domain);
        return ResponseEntity.ok().body(dto);
    }
}
