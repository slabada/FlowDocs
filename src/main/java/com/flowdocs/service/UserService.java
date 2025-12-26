package com.flowdocs.service;

import com.flowDocs.model.UserDto;
import com.flowdocs.model.UserModel;

public interface UserService {

    UserDto getUser(Long id);

    Boolean existsByEmail(String email);

    UserModel createUser(UserModel userDto);

    UserModel findByEmail(String email);
}
