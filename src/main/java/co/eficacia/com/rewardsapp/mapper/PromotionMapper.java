package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.dto.PromotionDTO;
import co.eficacia.com.rewardsapp.model.Promotion;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    Promotion fromPromotionDTO(PromotionDTO promotionDTO);

    Promotion fromPromotionDTO(UUID id, PromotionDTO promotionDTO);

    PromotionDTO fromPromotion(Promotion promotion);
}