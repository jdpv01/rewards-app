package co.eficacia.com.rewardsapp.validation;

import co.eficacia.com.rewardsapp.constant.RegistrationErrorCode;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.AuthenticationException;
import lombok.SneakyThrows;
import org.passay.*;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<CustomAnnotations.PasswordValidation, String> {

    @Override
    @SneakyThrows
    public boolean isValid(String password, ConstraintValidatorContext context) {
        final PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30)));
//                new CharacterRule(EnglishCharacterData.UpperCase, 1),
//                new CharacterRule(EnglishCharacterData.LowerCase, 1)
//                new CharacterRule(EnglishCharacterData.Digit, 1),
//                new CharacterRule(EnglishCharacterData.Special, 1),
//                new WhitespaceRule()));
        RuleResult ruleResult = passwordValidator.validate(new PasswordData(password));
        if (ruleResult.isValid()) {
            return true;
        }
        List<String> messages = passwordValidator.getMessages(ruleResult);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        throw new AuthenticationException(HttpStatus.UNAUTHORIZED, new ObjectError(RegistrationErrorCode.CODE_01, messageTemplate));
    }
}
