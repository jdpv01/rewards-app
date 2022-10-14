package co.eficacia.com.rewardsapp.api;

import co.eficacia.com.rewardsapp.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
public interface UserAPI {

    @PostMapping("/create-user")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/get-user/{id}")
    UserDTO getUser(@PathVariable UUID id);

    @GetMapping("/get-all-users")
    List<UserDTO> getUsers();

    @PostMapping("/update-user/{id}")
    UserDTO updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/delete-user/{id}")
    boolean deleteUser(@PathVariable UUID id);
}
