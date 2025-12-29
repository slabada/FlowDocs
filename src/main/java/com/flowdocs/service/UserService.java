package com.flowdocs.service;

import com.flowdocs.domain.UserDomain;
import com.flowdocs.model.UserModel;

import java.util.Optional;

public interface UserService {

    UserDomain getUser(Long id);

    Boolean existsByEmail(String email);

    UserDomain createUser(UserModel userDto);

    Optional<UserDomain> findByEmail(String email);
}
