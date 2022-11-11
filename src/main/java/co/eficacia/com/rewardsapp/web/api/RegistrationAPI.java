package co.eficacia.com.rewardsapp.web.api;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface RegistrationAPI {

    @GetMapping("/registration-confirm")
    public ModelAndView confirmRegistration(final HttpServletRequest request, final ModelMap model,
                                            @RequestParam("token") final String token) throws UnsupportedEncodingException;

    @GetMapping("/console")
    public ModelAndView console(final HttpServletRequest request,
                                final ModelMap model,
                                @RequestParam("messageKey") final Optional<String> messageKey);

    @GetMapping("/bad-user")
    public ModelAndView badUser(final HttpServletRequest request, final ModelMap model,
                                @RequestParam("messageKey" ) final Optional<String> messageKey,
                                @RequestParam("expired" ) final Optional<String> expired,
                                @RequestParam("token" ) final Optional<String> token);

    @GetMapping("/user/change-password")
    public ModelAndView showChangePasswordPage(final ModelMap model, @RequestParam("token") final String token);

    @GetMapping("/update-password")
    public ModelAndView updatePassword(final HttpServletRequest request, final ModelMap model,
                                       @RequestParam("messageKey" ) final Optional<String> messageKey);

    @GetMapping("/login")
    public ModelAndView login(final HttpServletRequest request, final ModelMap model,
                              @RequestParam("messageKey" ) final Optional<String> messageKey,
                              @RequestParam("error" ) final Optional<String> error);
}
