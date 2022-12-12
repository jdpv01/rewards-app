package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/users")
public interface UserAPI {

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/profile")
    UserDTO getUserProfile();

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-user/{id}")
    UserDTO getUser(@PathVariable UUID id);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-users")
    List<UserDTO> getUsers();

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/update-user")
    UserDTO updateUser(@RequestBody @Valid UserDTO userDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/{id}")
    boolean deleteUser(@PathVariable UUID id);
}