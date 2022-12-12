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
        User admin = new User("Admin", "Admin", "juandpv01@hotmail.com", passwordEncoder.encode("123456Aa"));
        Set<Role> adminRoleSet = new HashSet<>();
        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        adminRoleSet.add(adminRole);
        admin.setRoleSet(adminRoleSet);
        userRepository.save(admin);

        User user = new User("User", "User", "pelaezd86@gmail.com", passwordEncoder.encode("123456Aa"));
        Set<Role> userRoleSet = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        userRoleSet.add(userRole);
        user.setRoleSet(userRoleSet);
        userRepository.save(user);
        alreadySetup=true;
    }
}
