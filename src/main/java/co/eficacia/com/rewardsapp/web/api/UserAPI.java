package co.eficacia.com.rewardsapp.web.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

public interface UserAPI {

    @GetMapping("/logged-users")
    public String getLoggedUsers(final Locale locale, final Model model);

    @GetMapping("/logged-users-from-session-registry")
    public String getLoggedUsersFromSessionRegistry(final Locale locale, final Model model);
}
