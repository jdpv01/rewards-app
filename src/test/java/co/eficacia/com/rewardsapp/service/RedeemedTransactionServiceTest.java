package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.RedeemedTransaction;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.persistance.repository.RedeemedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.UserRepository;
import co.eficacia.com.rewardsapp.service.impl.RedeemedTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RedeemedTransactionServiceTest {

    private RedeemedTransactionService redeemedTransactionService;;

    private RedeemedTransactionRepository redeemedTransactionRepository;

    @BeforeEach
    public void init() {
        redeemedTransactionRepository = mock(RedeemedTransactionRepository.class);
        redeemedTransactionService = new RedeemedTransactionServiceImpl(redeemedTransactionRepository);
    }

    @Test
    public void redeemedCurrentPointsUser(){
        List<RedeemedTransaction> transactions = new ArrayList<>();
        RedeemedTransaction transaction1 = new RedeemedTransaction();
        RedeemedTransaction transaction2 = new RedeemedTransaction();
        User user1 = new User();
        user1.setRedeemedPoint(10);
        transaction1.setRedeemedPoints(200);
        transaction2.setRedeemedPoints(200);
        transaction1.setUser(user1);
        transaction2.setUser(user1);
        transactions.add(transaction1);
        transactions.add(transaction2);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(any())).thenReturn(user1);
        when(redeemedTransactionRepository.findAll()).thenReturn(transactions);
        when(redeemedTransactionRepository.save(any())).thenReturn(transactions);
        Integer esperado = 410;
        assertEquals(esperado, redeemedTransactionService.redeemedCurrentPointsUser(user1));
    }

}
