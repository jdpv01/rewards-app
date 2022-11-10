package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.validation.CustomAnnotations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    private String oldPassword;

    private  String token;

    @CustomAnnotations.PasswordValidation
    private String newPassword;
}
