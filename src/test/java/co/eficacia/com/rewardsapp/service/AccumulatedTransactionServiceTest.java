package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.*;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.impl.AccumulatedTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AccumulatedTransactionServiceTest {

    private AccumulatedTransactionService accumulatedTransactionService;

    private AccumulatedTransactionRepository accumulatedTransactionRepository;

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void init() {
        accumulatedTransactionRepository = mock(AccumulatedTransactionRepository.class);
        accumulatedTransactionService = new AccumulatedTransactionServiceImpl(accumulatedTransactionRepository);
        //userService = mock(UserService.class);
    }

    @Test
    public void testCurrentUserPoint() {
        List<AccumulatedTransaction> transactions = new ArrayList<>();
        AccumulatedTransaction transaction1 = new AccumulatedTransaction();
        AccumulatedTransaction transaction2 = new AccumulatedTransaction();
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setCurrentPoints(10);
        transaction1.setUser(user1);
        transaction2.setUser(user1);
        transaction1.setAccumulatedPoints(3231);
        transaction2.setAccumulatedPoints(3124);
        transactions.add(transaction1);
        transactions.add(transaction2);
        UserRepository userRepository = mock(UserRepository.class);
        when(userService.updateUser(user1)).thenReturn(user1);
        when(userRepository.save(any())).thenReturn(user1);
        when(accumulatedTransactionRepository.findAll()).thenReturn(transactions);
        when(accumulatedTransactionRepository.save(any())).thenReturn(transactions);
        User esperado =  accumulatedTransactionService.currentUserPoint(user1);
        int result = 6365;
        assertEquals(result,esperado.getCurrentPoints());
    }

    @Test
    public void testAddTransactionComment() {
        User user1 = new User();
        Comment comment1 = new Comment();
        Publication publication1 = new Publication();
        AccumulatedTransaction transaction = accumulatedTransactionService.addTransactionComment(user1, publication1, comment1);
        assertEquals("Publication", transaction.getSource());
    }

    @Test
    public void testAddTransactionSurvey() {
        User user1 = new User();
        Survey survey = new Survey();
        AccumulatedTransaction transaction = accumulatedTransactionService.addTransactionSurvey(user1, survey);
        assertEquals("Survey", transaction.getSource());
    }

}
