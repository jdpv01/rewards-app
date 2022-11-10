package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.persistance.model.Privilege;
import co.eficacia.com.rewardsapp.persistance.model.Role;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.security.SecurityUserService;
import co.eficacia.com.rewardsapp.service.UserService;
import co.eficacia.com.rewardsapp.web.api.RegistrationAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RegistrationController implements RegistrationAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUserService securityUserService;

    @Autowired
    private MessageSource messageSource;

    public RegistrationController() {
        super();
    }

    @Override
    public ModelAndView confirmRegistration(final HttpServletRequest request, final ModelMap model, final String token) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        model.addAttribute("lang", locale.getLanguage());
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
            authWithoutPassword(user);
            model.addAttribute("messageKey", "message.accountVerified");
            return new ModelAndView("redirect:/console", model);
        }
        model.addAttribute("messageKey", "auth.message." + result);
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return new ModelAndView("redirect:/badUser", model);
    }

    @Override
    public ModelAndView console(final HttpServletRequest request, final ModelMap model, final Optional<String> messageKey) {
        Locale locale = request.getLocale();
        messageKey.ifPresent( key -> {
                    String message = messageSource.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );
        return new ModelAndView("console", model);
    }

    @Override
    public ModelAndView badUser(final HttpServletRequest request, final ModelMap model, final Optional<String> messageKey, final Optional<String> expired, final Optional<String> token) {
        Locale locale = request.getLocale();
        messageKey.ifPresent( key -> {
                    String message = messageSource.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );
        expired.ifPresent( e -> model.addAttribute("expired", e));
        token.ifPresent( t -> model.addAttribute("token", t));
        return new ModelAndView("badUser", model);
    }

    @Override
    public ModelAndView showChangePasswordPage(final ModelMap model, final String token) {
        final String result = securityUserService.validatePasswordResetToken(token);
        if(result != null) {
            String messageKey = "auth.message." + result;
            model.addAttribute("messageKey", messageKey);
            return new ModelAndView("redirect:/login", model);
        } else {
            model.addAttribute("token", token);
            return new ModelAndView("redirect:/update-password");
        }
    }

    @Override
    public ModelAndView updatePassword(final HttpServletRequest request, final ModelMap model, final Optional<String> messageKey) {
        Locale locale = request.getLocale();
        model.addAttribute("lang", locale.getLanguage());
        messageKey.ifPresent( key -> {
                    String message = messageSource.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );
        return new ModelAndView("updatePassword", model);
    }

    @Override
    public ModelAndView login(final HttpServletRequest request, final ModelMap model, final Optional<String> messageKey, final Optional<String> error) {
        Locale locale = request.getLocale();
        model.addAttribute("lang", locale.getLanguage());
        messageKey.ifPresent( key -> {
                    String message = messageSource.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );
        error.ifPresent( e ->  model.addAttribute("error", e));
        return new ModelAndView("login", model);
    }

    // ============== NON-API ============

    public void authWithoutPassword(User user) {
        List<Privilege> privileges = user.getRoleCollection()
                .stream()
                .map(Role::getPrivilegeCollection)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = privileges.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
