package co.eficacia.com.rewardsapp.repository;

import co.eficacia.com.rewardsapp.model.RedeemedTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RedeemedTransactionRepository extends CrudRepository<RedeemedTransaction, UUID> {
}
