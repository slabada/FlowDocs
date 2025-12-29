package com.flowdocs.service;

import com.flowdocs.domain.UserDomain;
import com.flowdocs.exception.UserException;
import com.flowdocs.mapper.UserMapper;
import com.flowdocs.model.UserModel;
import com.flowdocs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDomain getUser(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(UserException.NullUserException::new);
        return userMapper.toDomain(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDomain createUser(UserModel userModel) {
        var model = userRepository.save(userModel);
        return userMapper.toDomain(model);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }
}
