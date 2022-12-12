package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Comment;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.persistance.model.User;

import java.util.List;
import java.util.UUID;

public interface PublicationService {

    Publication createPublication(Publication publication);

    Publication getPublication(UUID id);

    List<Publication> getPublications();

    Publication updatePublication(Publication publication);

    boolean deletePublication(UUID id);
}
