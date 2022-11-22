package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.PromotionRepository;
import co.eficacia.com.rewardsapp.service.impl.AccumulatedTransactionServiceImpl;
import co.eficacia.com.rewardsapp.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<Promotion> listPromotion = new ArrayList<>();
        Promotion promotion1 = new Promotion();
        Promotion promotion2 = new Promotion();
        Promotion promotion3 = new Promotion();
        promotion1.setName(nombrePrueba1);
        promotion2.setName(nombrePrueba2);
        promotion3.setName(nombrePrueba2);
        listPromotion.add(promotion1);
        listPromotion.add(promotion2);
        listPromotion.add(promotion3);
        when(promotionRepository.save(promotion1)).thenReturn(promotion1);
        when(promotionRepository.save(promotion2)).thenReturn(promotion2);
        when(promotionRepository.save(promotion3)).thenReturn(promotion3);
        when(promotionRepository.findAll()).thenReturn(listPromotion);
        System.out.println(listPromotion.size());
        System.out.println(listPromotion);
        List<Promotion> resultado = promotionService.searchPromotion(nombrePrueba1);
        System.out.println(resultado);
        System.out.println(resultado.size());
        int indice = 1;
        assertEquals(nombrePrueba1, resultado.get(indice).getName());
        //assertEquals(nombrePrueba2, promotionService.searchPromotion(nombrePrueba2).get(0).getName());
    }
}
