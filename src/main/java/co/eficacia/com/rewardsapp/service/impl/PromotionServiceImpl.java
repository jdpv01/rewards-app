package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.PromotionErrorCode;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.repository.PromotionRepository;
import co.eficacia.com.rewardsapp.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotion(UUID id) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        if(optionalPromotion.isPresent())
            return optionalPromotion.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(PromotionErrorCode.CODE_01, PromotionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Promotion> getPromotions() {
        return StreamSupport.stream(promotionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(promotion.getId());
        if(optionalPromotion.isPresent())
            return promotionRepository.save(promotion);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(PromotionErrorCode.CODE_01, PromotionErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deletePromotion(UUID id) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        if(optionalPromotion.isPresent()) {
            promotionRepository.delete(optionalPromotion.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(PromotionErrorCode.CODE_01, PromotionErrorCode.CODE_01.getMessage()));
    }
}
