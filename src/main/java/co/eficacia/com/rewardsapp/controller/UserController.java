package co.eficacia.com.rewardsapp.controller;

import co.eficacia.com.rewardsapp.api.UserAPI;
import co.eficacia.com.rewardsapp.dto.UserDTO;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        return userMapper.fromUser(userService.updateUser(userMapper.fromUserDTO(id, userDTO)));
    }

    @Override
    public boolean deleteUser(UUID id) {
        return userService.deleteUser(id);
    }
}
