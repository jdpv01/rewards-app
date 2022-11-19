package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.impl.AccumulatedTransactionServiceImpl;
import co.eficacia.com.rewardsapp.persistance.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccumulatedTransactionServiceTest {

    private AccumulatedTransactionService accumulatedTransactionService;

    private AccumulatedTransactionRepository accumulatedTransactionRepository;

    @BeforeEach
    public void init() {
        accumulatedTransactionRepository = mock(AccumulatedTransactionRepository.class);
        accumulatedTransactionService = new AccumulatedTransactionServiceImpl(accumulatedTransactionRepository);
    }

    @Test
    public void testCurrentUserPoint() {
        List<AccumulatedTransaction> transactions = new ArrayList<>();
        AccumulatedTransaction transaction1 = new AccumulatedTransaction();
        AccumulatedTransaction transaction2 = new AccumulatedTransaction();
        User user1 = new User();
        transaction1.setUser(user1);
        transaction2.setUser(user1);
        transaction1.setAccumulatedPoints(3231);
        transaction2.setAccumulatedPoints(3124);
        transactions.add(transaction1);
        transactions.add(transaction2);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(any())).thenReturn(user1);
        when(accumulatedTransactionRepository.findAll()).thenReturn(transactions);
        assertEquals(6355, accumulatedTransactionService.currentUserPoint(user1));
    }

    @Test
    public void testAddTransactionComment() {

    }

    @Test
    public void testAddTransactionSurvey() {

    }

}
