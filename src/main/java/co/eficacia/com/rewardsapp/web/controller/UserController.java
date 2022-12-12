package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.persistance.model.UserDetailsImpl;
import co.eficacia.com.rewardsapp.web.api.UserAPI;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserController implements UserAPI {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return userMapper.fromUser(userService.createUser(userMapper.fromUserDTO(userDTO)));
    }

    @Override
    public UserDTO getUser(UUID id) {
        return userMapper.fromUser(userService.getUser(id));
    }

    @Override
    public UserDTO getUserProfile(){
        return userMapper.fromUser(userService.getSignedInUser());
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        return userMapper.fromUser(userService.updateUser(userMapper.fromUserDTO(userDetails.getUser().getId(), userDTO)));
    }

    @Override
    public boolean deleteUser(UUID id) {
        return userService.deleteUser(id);
    }
}