package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.UserErrorCode;
import co.eficacia.com.rewardsapp.error.exception.EntityError;
import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.model.User;
import co.eficacia.com.rewardsapp.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent())
            return optionalUser.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent())
            return userRepository.save(user);
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }
}
