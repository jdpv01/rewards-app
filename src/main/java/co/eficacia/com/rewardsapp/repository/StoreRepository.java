package co.eficacia.com.rewardsapp.repository;

import co.eficacia.com.rewardsapp.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StoreRepository extends CrudRepository<Store, UUID> {
}