package co.eficacia.com.rewardsapp.persistance.repository;

import co.eficacia.com.rewardsapp.constant.RoleEnum;
import co.eficacia.com.rewardsapp.persistance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}


