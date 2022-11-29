package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.UserErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.UserService;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

       return UserDetailsImpl.build(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent())
            return optionalUser.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
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
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }
}
