package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.PromotionRepository;
import co.eficacia.com.rewardsapp.service.impl.AccumulatedTransactionServiceImpl;
import co.eficacia.com.rewardsapp.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PromotionServiceTest {

    private PromotionService promotionService;

    private PromotionRepository promotionRepository;

    @BeforeEach
    public void init() {
        promotionRepository = mock(PromotionRepository.class);
        promotionService = new PromotionServiceImpl(promotionRepository);
    }

    @Test
    public void testSearchPromotion() {

    }
}
