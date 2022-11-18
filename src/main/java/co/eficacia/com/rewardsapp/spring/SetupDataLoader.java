package co.eficacia.com.rewardsapp.spring;

import co.eficacia.com.rewardsapp.constant.RoleEnum;
import co.eficacia.com.rewardsapp.persistance.model.Role;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.RoleRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        User adminUser = new User("juandpv01@hotmail.com", passwordEncoder.encode("123456Aa"));
        Set<Role> roleSet = new HashSet<>();
        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roleSet.add(adminRole);
        adminUser.setRoleSet(roleSet);
        userRepository.save(adminUser);
        alreadySetup=true;
    }
}
