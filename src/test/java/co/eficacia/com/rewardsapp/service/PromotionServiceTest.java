package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.repository.PromotionRepository;
import co.eficacia.com.rewardsapp.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        String nombrePrueba1 = "Colgate Black";
        String nombrePrueba2 = "Jamon Zenu";
        Promotion promotion1 = new Promotion();
        Promotion promotion2 = new Promotion();
        Promotion promotion3 = new Promotion();
        promotion1.setName(nombrePrueba1);
        promotion2.setName(nombrePrueba2);
        when(promotionRepository.findById(any())).thenReturn(Optional.of(promotion1));
        when(promotionRepository.findById(any())).thenReturn(Optional.of(promotion2));
        when(promotionRepository.save(any())).thenReturn(promotion1);
        when(promotionRepository.save(any())).thenReturn(promotion2);
        Optional<Promotion> resultado = promotionService.searchPromotion(nombrePrueba1);
        assertEquals(nombrePrueba1, resultado.get().getName());
        //assertEquals(nombrePrueba2, promotionService.searchPromotion(nombrePrueba2).get(0).getName());
    }
}
