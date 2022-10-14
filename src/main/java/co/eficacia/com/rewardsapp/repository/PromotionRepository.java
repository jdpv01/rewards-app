package co.eficacia.com.rewardsapp.repository;

import co.eficacia.com.rewardsapp.model.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepository  extends CrudRepository<Promotion, UUID> {
}
