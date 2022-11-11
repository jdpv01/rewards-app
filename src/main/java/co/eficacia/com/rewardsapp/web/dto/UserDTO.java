package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.validation.CustomAnnotations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CustomAnnotations.PasswordMatchesValidation
public class UserDTO {

    @NotNull
    @Size(min = 1, message = "{Size.userDTO.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDTO.lastName}")
    private String lastName;

    @CustomAnnotations.PasswordValidation
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @CustomAnnotations.EmailValidation
    @NotNull
    @Size(min = 1, message = "{Size.userDTO.email}")
    private String email;

    private Integer role;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDTO [firstName=")
                .append(firstName)
                .append(", lastName=")
                .append(lastName)
                .append(", email=")
                .append(email)
                .append(", role=")
                .append(role).append("]");
        return builder.toString();
    }
}