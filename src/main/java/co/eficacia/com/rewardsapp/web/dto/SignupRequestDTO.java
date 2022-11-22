package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.validation.CustomAnnotations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String name;

    private String lastName;
    
    private Set<String> role;

    @NotBlank
    @Size(min = 8)
    @Size(max = 30)
    @CustomAnnotations.PasswordValidation
    private String password;
}
