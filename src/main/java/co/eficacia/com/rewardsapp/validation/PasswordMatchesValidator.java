package co.eficacia.com.rewardsapp.validation;

import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<CustomAnnotations.PasswordMatchesValidation, Object> {

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDTO userDTO = (UserDTO) obj;
        return userDTO.getPassword().equals(userDTO.getMatchingPassword());
    }
}
