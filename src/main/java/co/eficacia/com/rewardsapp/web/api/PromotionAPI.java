package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.web.dto.PromotionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/promotions")
public interface PromotionAPI {

    @PostMapping("/create-promotion")
    PromotionDTO createPromotion(@RequestBody PromotionDTO promotionDTO);

    @GetMapping("/get-promotion/{id}")
    PromotionDTO getPromotion(@PathVariable UUID id);

    @GetMapping("/get-all-promotions")
    List<PromotionDTO> getPromotions();

    @PutMapping("/update-promotion/{id}")
    PromotionDTO updatePromotion(@PathVariable UUID id, @RequestBody PromotionDTO promotionDTO);

    @DeleteMapping("/delete-promotion/{id}")
    boolean deletePromotion(@PathVariable UUID id);

    @GetMapping("/search-promotions-by-name")
    Optional<Promotion> searchPromotion(String line);

}
