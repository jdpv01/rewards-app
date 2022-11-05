package co.eficacia.com.rewardsapp.repository;

import co.eficacia.com.rewardsapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{

    User findByEmail(String email);
}
