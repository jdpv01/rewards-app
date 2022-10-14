package co.eficacia.com.rewardsapp.controller;

import co.eficacia.com.rewardsapp.api.PromotionAPI;
import co.eficacia.com.rewardsapp.dto.PromotionDTO;
import co.eficacia.com.rewardsapp.mapper.PromotionMapper;
import co.eficacia.com.rewardsapp.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PromotionController implements PromotionAPI {

    private final PromotionService promotionService;
    private final PromotionMapper promotionMapper;

    @Override
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        return promotionMapper.fromPromotion(promotionService.createPromotion(promotionMapper.fromPromotionDTO(promotionDTO)));
    }

    @Override
    public PromotionDTO getPromotion(UUID id) {
        return promotionMapper.fromPromotion(promotionService.getPromotion(id));
    }

    @Override
    public List<PromotionDTO> getPromotions() {
        return promotionService.getPromotions().stream().map(promotionMapper::fromPromotion).collect(Collectors.toList());
    }

    @Override
    public PromotionDTO updatePromotion(UUID id, PromotionDTO promotionDTO) {
        return promotionMapper.fromPromotion(promotionService.updatePromotion(promotionMapper.fromPromotionDTO(id, promotionDTO)));
    }

    @Override
    public boolean deletePromotion(UUID id) {
        return promotionService.deletePromotion(id);
    }
}
