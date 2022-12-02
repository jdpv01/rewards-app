package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.repository.RedeemedTransactionRepository;
import co.eficacia.com.rewardsapp.service.impl.RedeemedTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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

    }

}
