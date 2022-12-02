package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Answer;
import co.eficacia.com.rewardsapp.persistance.model.Comment;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.web.dto.CommentDTO;
import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface PublicationService {

    Publication createPublication(Publication publication);

    Publication getPublication(UUID id);

    List<Publication> getPublications();

    Publication updatePublication(Publication publication);

    boolean deletePublication(UUID id);

    void addCommentToPublication(Publication publication, Comment comment, User user);
}
