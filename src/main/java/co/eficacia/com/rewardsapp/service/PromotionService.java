package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.Promotion;

import java.util.List;
import java.util.UUID;

public interface PromotionService {

    Promotion createPromotion(Promotion promotion);

    Promotion getPromotion(UUID id);

    List<Promotion> getPromotions();

    Promotion updatePromotion(Promotion promotion);

    boolean deletePromotion(UUID id);

    List<Promotion> searchPromotion(String line);
}
