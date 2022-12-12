package co.eficacia.com.rewardsapp.spring;

import co.eficacia.com.rewardsapp.constant.RoleEnum;
import co.eficacia.com.rewardsapp.persistance.model.Role;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.RoleRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        User user = new User("Juan", "Peláez", "juandpv01@hotmail.com", passwordEncoder.encode("123456Aa"));
        Set<Role> roleSet = new HashSet<>();
        Role role = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roleSet.add(role);
        user.setRoleSet(roleSet);
        userRepository.save(user);
        alreadySetup=true;
    }
}
