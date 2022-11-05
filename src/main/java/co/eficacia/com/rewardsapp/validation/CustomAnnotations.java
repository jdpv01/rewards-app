package co.eficacia.com.rewardsapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public interface CustomAnnotations {

    @Target({ TYPE, FIELD, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Constraint(validatedBy = PasswordConstraintValidator.class)
    @Documented
    @interface PasswordValidation {

        String message() default "Invalid Password";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ TYPE, FIELD, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Constraint(validatedBy = EmailValidator.class)
    @Documented
    @interface EmailValidation {

        String message() default "Invalid Email";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Constraint(validatedBy = PasswordMatchesValidator.class)
    @Documented
    public @interface PasswordMatchesValidation {

        String message() default "Passwords don't match";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}




