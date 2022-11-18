package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.validation.CustomAnnotations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @CustomAnnotations.PasswordValidation
    private String password;

    @Email
    @NotNull
    private String email;
}