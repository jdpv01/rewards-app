package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.model.VerificationToken;
import co.eficacia.com.rewardsapp.registration.OnRegistrationCompleteEvent;
import co.eficacia.com.rewardsapp.security.SecurityUserService;
import co.eficacia.com.rewardsapp.service.UserService;
import co.eficacia.com.rewardsapp.web.api.RegistrationRestAPI;
import co.eficacia.com.rewardsapp.web.dto.PasswordDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import co.eficacia.com.rewardsapp.web.error.exception.InvalidOldPasswordException;
import co.eficacia.com.rewardsapp.web.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RegistrationRestController implements RegistrationRestAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUserService securityUserService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private Environment environment;

    public RegistrationRestController() {
        super();
    }

    @Override
    public GenericResponse registerUserAccount(final UserDTO userDTO, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", userDTO);
        final User registeredUser = userService.registerNewUserAccount(userDTO);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }

    @Override
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, final String existingToken) {
        final VerificationToken verificationToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(verificationToken.getToken());
        javaMailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), verificationToken, user));
        return new GenericResponse(messageSource.getMessage("message.resendToken", null, request.getLocale()));
    }

    @Override
    public GenericResponse resetPassword(final HttpServletRequest request, final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            javaMailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
        }
        return new GenericResponse(messageSource.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    @Override
    public GenericResponse savePassword(final Locale locale, PasswordDTO passwordDTO) {
        final String result = securityUserService.validatePasswordResetToken(passwordDTO.getToken());
        if(result != null) {
            return new GenericResponse(messageSource.getMessage("auth.message." + result, null, locale));
        }
        Optional<User> user = userService.getUserByPasswordResetToken(passwordDTO.getToken());
        if(user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDTO.getNewPassword());
            return new GenericResponse(messageSource.getMessage("message.resetPasswordSuc", null, locale));
        } else {
            return new GenericResponse(messageSource.getMessage("auth.message.invalid", null, locale));
        }
    }

    @Override
    public GenericResponse updatePassword(final Locale locale, PasswordDTO passwordDTO) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDTO.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDTO.getNewPassword());
        return new GenericResponse(messageSource.getMessage("message.updatePasswordSuc", null, locale));
    }

    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registration-confirm.html?token=" + newToken.getToken();
        final String message = messageSource.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/change-password?token=" + token;
        final String message = messageSource.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(Objects.requireNonNull(environment.getProperty("support.email")));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
