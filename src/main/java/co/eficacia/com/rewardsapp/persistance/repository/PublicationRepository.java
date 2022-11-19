package co.eficacia.com.rewardsapp.persistance.repository;

import co.eficacia.com.rewardsapp.persistance.model.Publication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, UUID> {
}
