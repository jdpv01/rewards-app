package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.dto.UserDTO;
import co.eficacia.com.rewardsapp.model.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserDTO(UserDTO userDTO);

    User fromUserDTO(UUID id, UserDTO userDTO);

    UserDTO fromUser(User user);
}
