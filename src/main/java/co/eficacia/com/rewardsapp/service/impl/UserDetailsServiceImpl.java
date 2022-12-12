package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.UserErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.Reward;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.model.UserDetailsImpl;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.UserService;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
       List<GrantedAuthority> authorities = user.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
       return new UserDetailsImpl(user, authorities);
    }

    @Override
    @Transactional
    public User getSignedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
            return userDetails.getUser();
        }else{
            throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent())
            return userRepository.save(user);
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, new ObjectError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()));
    }

    @Override
    public void accumulatePoints(User user, Invoice invoice) {
        user.setCurrentPoints(user.getCurrentPoints()+invoice.getPendingPoints());
    }

    @Override
    public void spendPoints(User user, Reward reward) {
        user.setCurrentPoints(user.getCurrentPoints()-reward.getRequiredPoints());
    }
}
