package co.eficacia.com.rewardsapp.persistance.repository;

import co.eficacia.com.rewardsapp.persistance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);
}


