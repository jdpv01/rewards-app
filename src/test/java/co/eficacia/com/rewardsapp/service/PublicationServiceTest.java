package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.persistance.model.Comment;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.PublicationRepository;
import co.eficacia.com.rewardsapp.service.impl.AccumulatedTransactionServiceImpl;
import co.eficacia.com.rewardsapp.service.impl.PublicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

public class PublicationServiceTest {

    private PublicationService publicationService;

    private PublicationRepository publicationRepository;

    @BeforeEach
    public void init() {
        publicationRepository = mock(PublicationRepository.class);
        publicationService = new PublicationServiceImpl(publicationRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllPublications() {
        Publication publication1 = new Publication();
        List<Publication> listPublications = new ArrayList<>();
        listPublications.add(publication1);
        when(publicationRepository.findAll()).thenReturn(listPublications);
        assertEquals(1, publicationService.getPublications().size());
        verify(publicationRepository, times(1)).findAll();

    }

    @Test
    public void testCreatePublications() {
        Publication publication1 = new Publication();
        when(publicationRepository.save(any())).thenReturn(publication1);
        assertEquals(publication1, publicationService.createPublication(publication1));
    }


    @Test
    public void testCommentToPublication() {
        //AccumulatedTransactionServiceImpl transactions = mock(AccumulatedTransactionServiceImpl.class);
        AccumulatedTransaction transaction = new AccumulatedTransaction();
        User user = new User();
        Publication publication1 = new Publication();
        List<Comment> listCommentsPublications = new ArrayList<>();
        publication1.setCommentList(listCommentsPublications);
        Comment comment1 = new Comment();
        //when(transactions.addTransactionComment(user, publication1, comment1)).thenReturn(transaction);
        when(publicationRepository.save(any())).thenReturn(publication1);
        publicationService.addCommentToPublication(publication1, comment1, user);
        assertEquals(user, comment1.getUser());
        assertEquals(publication1.getCommentList().get(0), comment1);
    }


}
