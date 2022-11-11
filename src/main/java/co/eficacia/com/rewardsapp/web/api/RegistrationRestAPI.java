package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.PasswordDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import co.eficacia.com.rewardsapp.web.util.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RequestMapping("/user")
public interface RegistrationRestAPI {

    @PostMapping("/register-user-account")
    public GenericResponse registerUserAccount(@Valid final UserDTO userDTO, final HttpServletRequest request);

    // User activation - verification
    @GetMapping("/resend-registration-token")
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken);

    @PostMapping("/reset-password")
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail);

    @PostMapping("/save-password")
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDTO passwordDTO);

    @PostMapping("/update-password")
    public GenericResponse updatePassword(final Locale locale, @Valid PasswordDTO passwordDTO);
}
