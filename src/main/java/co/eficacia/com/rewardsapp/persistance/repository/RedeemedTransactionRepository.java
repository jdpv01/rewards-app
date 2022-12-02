package co.eficacia.com.rewardsapp.persistance.repository;

import co.eficacia.com.rewardsapp.persistance.model.RedeemedTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RedeemedTransactionRepository extends CrudRepository<RedeemedTransaction, UUID> {
}
