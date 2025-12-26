package com.flowdocs.service;

import com.flowDocs.model.UserDto;
import com.flowdocs.exception.UserException;
import com.flowdocs.model.UserModel;
import com.flowdocs.mapper.UserMapper;
import com.flowdocs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto getUser(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(UserException.NullUserException::new);
        return userMapper.toDto(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserException.NullUserException::new);
    }
}
